package repositories;

import data.PostgresDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlightRepository {

    public boolean exists(int id) {
        String sql = "SELECT 1 FROM flights WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("❌ Flight exists error: " + e.getMessage());
            return false;
        }
    }

    public void showAll() {
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

    public Integer getPrice(int flightId) {
        String sql = "SELECT price FROM flights WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("price");
            }
        } catch (Exception e) {
            System.out.println("❌ Error getting price: " + e.getMessage());
        }
        return null;
    }
}
