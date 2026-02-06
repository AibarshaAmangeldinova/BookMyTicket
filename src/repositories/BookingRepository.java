package repositories;

import data.PostgresDB;
import models.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingRepository {

    public boolean isSeatTaken(int flightId, String seatNumber) {

        String sql = "SELECT 1 FROM bookings WHERE flight_id = ? AND seat_number = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);
            ps.setString(2, seatNumber);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Seat check error");
            return true;
        }
    }

    public int saveAndReturnId(Booking b) {

        String sql =
                "INSERT INTO bookings " +
                        "(flight_id, first_name, last_name, phone, document_type, document_number, seat_number, ticket_class) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, b.flightId);
            ps.setString(2, b.firstName);
            ps.setString(3, b.lastName);
            ps.setString(4, b.phone);
            ps.setString(5, b.documentType);
            ps.setString(6, b.documentNumber);
            ps.setString(7, b.seatNumber);
            ps.setString(8, b.ticketClass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");

        } catch (Exception e) {
            System.out.println("Save booking error");
        }

        return -1;
    }

    public boolean deleteById(int bookingId) {

        String sql = "DELETE FROM bookings WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Delete error");
            return false;
        }
    }

    public int getFlightIdByBookingId(int bookingId) {

        String sql = "SELECT flight_id FROM bookings WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt("flight_id");

        } catch (Exception e) {
            System.out.println("Find booking error");
        }

        return -1;
    }
}
