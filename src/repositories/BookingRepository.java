package com.company.repositories;

import com.company.data.PostgresDB;
import com.company.models.Booking;

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
            ps.setInt(1, booking.flightId);
            ps.setString(2, booking.passengerName);
            ps.setString(3, booking.seatNumber);
            ps.setString(4, booking.ticketClass);
            ps.setString(5, booking.documentType);

            ps.executeUpdate();
            System.out.println("Booking saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

