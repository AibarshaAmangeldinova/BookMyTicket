package repositories;

import data.Db;
import dto.FullBookingDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    private final CategoryRepository categoryRepo = new CategoryRepository();

    public boolean exists(int flightId) {
        String sql = "SELECT 1 FROM flights WHERE id=?";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return false;
            ps.setInt(1, flightId);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getPrice(int flightId) {
        String sql = "SELECT price FROM flights WHERE id=?";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return null;
            ps.setInt(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("price");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public List<String[]> getFlightsForTable() {
        String sql = """
            SELECT f.id, f.origin, f.destination, COALESCE(c.name,'-') AS category, f.price
            FROM flights f
            LEFT JOIN flight_categories c ON c.id = f.category_id
            ORDER BY f.id
        """;
        List<String[]> rows = new ArrayList<>();
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return rows;
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new String[]{
                            String.valueOf(rs.getInt("id")),
                            rs.getString("origin"),
                            rs.getString("destination"),
                            rs.getString("category"),
                            String.valueOf(rs.getInt("price"))
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("Flights load error: " + e.getMessage());
        }
        return rows;
    }

    public boolean addFlight(String origin, String destination, int price, String categoryName) {
        Integer catId = categoryRepo.findIdByName(categoryName);
        if (catId == null) return false;

        String sql = "INSERT INTO flights(origin,destination,price,category_id) VALUES (?,?,?,?)";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return false;
            ps.setString(1, origin);
            ps.setString(2, destination);
            ps.setInt(3, price);
            ps.setInt(4, catId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    //  JOIN criterion: bookings + users + flights + categories
    public FullBookingDescription getFullBookingDescription(int bookingId) {
        String sql = """
            SELECT
              b.id AS booking_id, b.status, b.created_at,
              b.seat_number, b.ticket_class,
              b.document_type, b.phone, b.document_number,
              u.first_name, u.last_name,
              f.origin, f.destination, f.price,
              COALESCE(c.name,'-') AS category
            FROM bookings b
            JOIN users u ON u.id = b.user_id
            JOIN flights f ON f.id = b.flight_id
            LEFT JOIN flight_categories c ON c.id = f.category_id
            WHERE b.id = ?
        """;

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {

            if (con == null) return null;
            ps.setInt(1, bookingId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                FullBookingDescription d = new FullBookingDescription();
                d.bookingId = rs.getInt("booking_id");
                d.status = rs.getString("status");
                d.createdAt = rs.getString("created_at");

                d.passengerName = rs.getString("first_name") + " " + rs.getString("last_name");
                d.phone = rs.getString("phone");
                d.documentType = rs.getString("document_type");
                d.documentNumber = rs.getString("document_number");

                d.origin = rs.getString("origin");
                d.destination = rs.getString("destination");
                d.price = rs.getInt("price");
                d.category = rs.getString("category");

                d.seatNumber = rs.getString("seat_number");
                d.ticketClass = rs.getString("ticket_class");

                return d;
            }
        } catch (Exception e) {
            System.out.println("JOIN error: " + e.getMessage());
            return null;
        }
    }
}
