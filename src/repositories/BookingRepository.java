package repositories;

import com.company.data.PostgresDB;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingRepository {

    PostgresDB db = new PostgresDB();

    public void bookTicket(int flightId, String name) {
        String sql = "INSERT INTO bookings(flight_id, passenger_name) VALUES (?, ?)";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);
            ps.setString(2, name);
            ps.executeUpdate();

            System.out.println("Ticket booked successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
