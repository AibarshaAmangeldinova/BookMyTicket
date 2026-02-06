package repositories;

import data.PostgresDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeatRepository {

    public boolean isSeatTaken(int flightId, String seatNumber) {
        String sql = "SELECT 1 FROM bookings WHERE flight_id = ? AND seat_number = ? LIMIT 1";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);
            ps.setString(2, seatNumber);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("❌ Seat check error: " + e.getMessage());
            return true;
        }
    }
}
