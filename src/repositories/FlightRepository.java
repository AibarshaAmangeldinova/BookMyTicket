package repositories;

import data.PostgresDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlightRepository {

    public void showAllFlights() {
        String sql = "SELECT id, origin, destination, price FROM flights ORDER BY id";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== Available flights ===");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("origin") + " -> " +
                                rs.getString("destination") + " | " +
                                rs.getInt("price")
                );
            }

        } catch (Exception e) {
            System.out.println("❌ Error loading flights: " + e.getMessage());
        }
    }

    public int getFlightPrice(int flightId) {
        String sql = "SELECT price FROM flights WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("price");
            }

        } catch (Exception e) {
            System.out.println("❌ Error getting flight price: " + e.getMessage());
        }

        return -1;
    }
}
