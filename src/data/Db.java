package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    private static Db instance;

    private final String url = "jdbc:postgresql://localhost:5432/bookmyticket";
    private final String user = "postgres";
    private final String password = "0000";

    private Db() { }

    public static synchronized Db getInstance() {
        if (instance == null) instance = new Db();
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("DB connection error: " + e.getMessage());
            return null;
        }
    }
}

