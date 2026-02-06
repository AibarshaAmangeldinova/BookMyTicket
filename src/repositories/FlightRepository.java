package repositories;

import data.PostgresDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlightRepository {

    public void showAllFlights() {

        String sql = "SELECT id, from_city, to_city, price FROM flights ORDER BY id";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            System.out.println("\n=== Available flights ===");

            while (rs.next()) {
                int id = rs.getInt("id");
                String origin = rs.getString("from_city");
                String destination = rs.getString("to_city");
                int price = rs.getInt("price");

                System.out.println(id + " | " + origin + " -> " + destination + " | " + price);
            }

        } catch (Exception e) {
            System.out.println("Error loading flights");
        }
    }

    public int getFlightPrice(int flightId) {

        String sql = "SELECT price FROM flights WHERE id = ?";

        try (Connection con = PostgresDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("price");

        } catch (Exception e) {
            System.out.println("Error getting flight price");
        }

        return -1;
    }
}
