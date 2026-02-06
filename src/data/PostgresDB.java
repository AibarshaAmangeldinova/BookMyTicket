package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {

    private static final String DB_URL = "jdbc:postgresql://localhost:5434/bookmyticket";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "0000";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Cannot connect to database");
            e.printStackTrace();
            return null;
        }
    }
}
