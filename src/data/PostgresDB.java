package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB {

    public Connection connect() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bookmyticket",
                    "postgres",
                    "0000"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
