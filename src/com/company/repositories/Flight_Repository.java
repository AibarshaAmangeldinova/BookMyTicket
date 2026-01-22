package com.company.repositories;

import com.company.data.PostgresDB;
import java.sql.*;

public class Flight_Repository {

    PostgresDB db = new PostgresDB();

    public void showFlights() {
        String sql = "SELECT * FROM flights";

        try (Connection con = db.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ". " +
                                rs.getString("from_city") + " -> " +
                                rs.getString("to_city") +
                                " | Price: " + rs.getInt("price")
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

