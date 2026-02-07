package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    private static Db instance;

    private static final String URL = "jdbc:postgresql://localhost:5432/bookmyticket";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    private Db() {
    }

    public static synchronized Db getInstance() {
        if (instance == null) {
            instance = new Db();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
            return null;
        }
    }
}
