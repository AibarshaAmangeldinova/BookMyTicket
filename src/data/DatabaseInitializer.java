package data;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() {
        try (Connection con = Db.getInstance().getConnection()) {
            if (con == null) return;

            try (Statement st = con.createStatement()) {
                st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS flight_categories (" +
                                " id SERIAL PRIMARY KEY," +
                                " name VARCHAR(50) UNIQUE NOT NULL" +
                                ")"
                );

                st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS flights (" +
                                " id SERIAL PRIMARY KEY," +
                                " origin VARCHAR(50) NOT NULL," +
                                " destination VARCHAR(50) NOT NULL," +
                                " price INT NOT NULL," +
                                " category_id INT REFERENCES flight_categories(id)" +
                                ")"
                );

                st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS users (" +
                                " id SERIAL PRIMARY KEY," +
                                " first_name VARCHAR(50) NOT NULL," +
                                " last_name VARCHAR(50) NOT NULL," +
                                " phone VARCHAR(30) NOT NULL," +
                                " document_type VARCHAR(20) NOT NULL," +
                                " document_number VARCHAR(30) NOT NULL" +
                                ")"
                );

                st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS bookings (" +
                                " id SERIAL PRIMARY KEY," +
                                " flight_id INT NOT NULL REFERENCES flights(id)," +
                                " user_id INT NOT NULL REFERENCES users(id)," +
                                " passenger_name VARCHAR(120) NOT NULL," +
                                " seat_number VARCHAR(10) NOT NULL," +
                                " ticket_class VARCHAR(20) NOT NULL," +
                                " document_type VARCHAR(20) NOT NULL," +
                                " phone VARCHAR(30) NOT NULL," +
                                " document_number VARCHAR(30) NOT NULL," +
                                " status VARCHAR(20) NOT NULL DEFAULT 'BOOKED'," +
                                " created_at TIMESTAMP NOT NULL DEFAULT NOW()" +
                                ")"
                );

                st.executeUpdate(
                        "CREATE UNIQUE INDEX IF NOT EXISTS uq_seat_per_flight " +
                                "ON bookings(flight_id, seat_number) WHERE status='BOOKED'"
                );

                // default categories
                st.executeUpdate("INSERT INTO flight_categories(name) VALUES ('Domestic') ON CONFLICT (name) DO NOTHING");
                st.executeUpdate("INSERT INTO flight_categories(name) VALUES ('International') ON CONFLICT (name) DO NOTHING");
            }

        } catch (Exception e) {
            System.out.println("Init error: " + e.getMessage());
        }
    }
}
// minor refactor by Mirlan

