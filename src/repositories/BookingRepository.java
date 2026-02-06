package repositories;

import data.Db;
import dto.FullBookingDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingRepository {

    public Integer createBooking(int flightId, int userId, String seat, String ticketClass) {
        String sql = """
            INSERT INTO bookings(flight_id, user_id, seat_number, ticket_class)
            VALUES (?,?,?,?)
            RETURNING id
        """;

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return null;

            ps.setInt(1, flightId);
            ps.setInt(2, userId);
            ps.setString(3, seat.trim().toUpperCase());
            ps.setString(4, ticketClass.trim().toUpperCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }

        } catch (Exception e) {
            System.out.println(" Booking save error: " + e.getMessage());
            return null;
        }
        return null;
    }

    public Integer getActiveFlightIdByBookingId(int bookingId) {
        String sql = "SELECT flight_id FROM bookings WHERE id=? AND status='BOOKED'";

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return null;

            ps.setInt(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("flight_id");
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status='CANCELED' WHERE id=? AND status='BOOKED'";

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) return false;

            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public int countActiveBookings() {
        String sql = "SELECT COUNT(*) FROM bookings WHERE status='BOOKED'";

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (con == null) return 0;
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    // Criterion #1: JOIN across bookings + users + flights + categories
    public FullBookingDescription getFullBooking(int bookingId) {
        String sql = """
            SELECT
              b.id AS booking_id, b.status, b.created_at,
              u.first_name, u.last_name, u.phone, u.document_type, u.document_number,
              f.origin, f.destination, f.price,
              COALESCE(c.name,'-') AS category,
              b.seat_number, b.ticket_class
            FROM bookings b
            JOIN users u ON u.id = b.user_id
            JOIN flights f ON f.id = b.flight_id
            LEFT JOIN flight_categories c ON c.id = f.category_id
            WHERE b.id = ?
        """;

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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
            System.out.println(" JOIN error: " + e.getMessage());
            return null;
        }
    }
}
