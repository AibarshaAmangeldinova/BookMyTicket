package com.company.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {

    private final String URL = "jdbc:postgresql://localhost:5432/bookmyticket";
    private final String USER = "postgres";
    private final String PASSWORD = "0000";

    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("DB ERROR: " + e.getMessage());
            return null;
        }
    }
}


