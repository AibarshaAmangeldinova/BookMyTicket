package repositories;

import data.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingRepository {

    public Integer createBooking(int flightId, int userId, String passengerName,
                                 String seat, String ticketClass,
                                 String docType, String phone, String docNumber) {

        String sql = """
            INSERT INTO bookings(flight_id,user_id,passenger_name,seat_number,ticket_class,document_type,phone,document_number,status)
            VALUES (?,?,?,?,?,?,?,?,'BOOKED')
            RETURNING id
        """;

        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {

            if (con == null) return null;

            ps.setInt(1, flightId);
            ps.setInt(2, userId);
            ps.setString(3, passengerName);
            ps.setString(4, seat.trim().toUpperCase());
            ps.setString(5, ticketClass.trim().toUpperCase());
            ps.setString(6, docType.trim().toUpperCase());
            ps.setString(7, phone.trim());
            ps.setString(8, docNumber.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Booking save error: " + e.getMessage());
            return null;
        }
        return null;
    }

    public Integer getFlightIdIfBooked(int bookingId) {
        String sql = "SELECT flight_id FROM bookings WHERE id=? AND status='BOOKED'";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
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
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
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
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql));
             ResultSet rs = (ps == null ? null : ps.executeQuery())) {
            if (con == null || rs == null) return 0;
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
}
