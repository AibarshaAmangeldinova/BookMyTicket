package repositories;

import data.PostgresDB;
import models.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    private Connection con = PostgresDB.getConnection();

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available flights:");

            while (rs.next()) {
                flights.add(new Flight(rs.getInt("id"),
                        rs.getString("from_city"),
                        rs.getString("to_city"),
                        rs.getInt("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }
}
