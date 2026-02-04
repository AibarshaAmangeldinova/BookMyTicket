package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/bookmyticket";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
