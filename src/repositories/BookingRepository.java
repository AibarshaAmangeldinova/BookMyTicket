package repositories;

import data.PostgresDB;
import models.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingRepository {

    public int saveAndReturnId(Booking b) {
        String sql = """
            INSERT INTO bookings
            (flight_id, passenger_name, seat_number, ticket_class, document_type, phone, document_number)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, b.flightId);
            ps.setString(2, b.passengerName);
            ps.setString(3, b.seatNumber);
            ps.setString(4, b.ticketClass);
            ps.setString(5, b.documentType);
            ps.setString(6, b.phone);
            ps.setString(7, b.documentNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("❌ Error saving booking: " + e.getMessage());
        }
        return -1;
    }

    public Integer getFlightIdByBookingId(int bookingId) {
        String sql = "SELECT flight_id FROM bookings WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("flight_id");
            }
        } catch (Exception e) {
            System.out.println("❌ Error finding booking: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteById(int bookingId) {
        String sql = "DELETE FROM bookings WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error deleting booking: " + e.getMessage());
            return false;
        }
    }
}
