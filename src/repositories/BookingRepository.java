package repositories;

import data.PostgresDB;
import models.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingRepository {

    private Connection con = PostgresDB.getConnection();

    public void save(Booking booking) {

        String sql = """
                INSERT INTO bookings
                (flight_id, passenger_name, seat_number, ticket_class, document_type)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, booking.getFlightId());
            ps.setString(2, booking.getPassengerName());
            ps.setString(3, booking.getSeatNumber());
            ps.setString(4, booking.getTicketClass());
            ps.setString(5, booking.getDocumentType());

            ps.executeUpdate();
            System.out.println("Booking saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

