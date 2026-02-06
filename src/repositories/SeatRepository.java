package repositories;

import data.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeatRepository {
    public boolean isSeatTaken(int flightId, String seatNumber) {
        String sql = "SELECT 1 FROM bookings WHERE flight_id=? AND seat_number=? AND status='BOOKED' LIMIT 1";
        try (Connection con = Db.getInstance().getConnection();
             PreparedStatement ps = (con == null ? null : con.prepareStatement(sql))) {
            if (con == null) return true;
            ps.setInt(1, flightId);
            ps.setString(2, seatNumber.trim().toUpperCase());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            return true;
        }
    }
}
