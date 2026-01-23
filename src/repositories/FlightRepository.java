package com.company.repositories;

import com.company.data.PostgresDB;
import com.company.models.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlightRepository {

    private Connection con = PostgresDB.getConnection();

    public void showAllFlights() {
        String sql = "SELECT * FROM flights";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available flights:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("from_city") + " -> " +
                                rs.getString("to_city") + " | " +
                                rs.getInt("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
