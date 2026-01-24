package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5434/BookMyTicket";
        String user = "postgres";
        String password = "0000";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, "testuser");
            st.setString(2, "1234");
            st.executeUpdate();

            System.out.println("DONE ✅ INSERT WORKED");

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
