package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    private static Db instance;

    private Db() {}

    public static Db getInstance() {
        if (instance == null) instance = new Db();
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASSWORD);
        } catch (Exception e) {
            System.out.println("DB connection error: " + e.getMessage());
            return null;
        }
    }
}
