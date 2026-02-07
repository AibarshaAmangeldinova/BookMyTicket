package data;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String CREATE_FLIGHT_CATEGORIES =
            "CREATE TABLE IF NOT EXISTS flight_categories (" +
                    " id SERIAL PRIMARY KEY," +
                    " name VARCHAR(50) UNIQUE NOT NULL" +
                    ")";

    private static final String CREATE_FLIGHTS =
            "CREATE TABLE IF NOT EXISTS flights (" +
                    " id SERIAL PRIMARY KEY," +
                    " origin VARCHAR(50) NOT NULL," +
                    " destination VARCHAR(50) NOT NULL," +
                    " price INT NOT NULL," +
                    " category_id INT REFERENCES flight_categories(id)" +
                    ")";

    private static final String CREATE_USERS =
            "CREATE TABLE IF NOT EXISTS users (" +
                    " id SERIAL PRIMARY KEY," +
                    " first_name VARCHAR(50) NOT NULL," +
                    " last_name VARCHAR(50) NOT NULL," +
                    " phone VARCHAR(30) NOT NULL," +
                    " document_type VARCHAR(20) NOT NULL," +
                    " document_number VARCHAR(30) NOT NULL" +
                    ")";

    private static final String CREATE_BOOKINGS =
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
                    ")";

    private static final String CREATE_UNIQUE_SEAT_INDEX =
            "CREATE UNIQUE INDEX IF NOT EXISTS uq_seat_per_flight " +
                    "ON bookings(flight_id, seat_number) WHERE status='BOOKED'";

    private static final String INSERT_CATEGORY_DOMESTIC =
            "INSERT INTO flight_categories(name) VALUES ('Domestic') " +
                    "ON CONFLICT (name) DO NOTHING";

    private static final String INSERT_CATEGORY_INTERNATIONAL =
            "INSERT INTO flight_categories(name) VALUES ('International') " +
                    "ON CONFLICT (name) DO NOTHING";

    public static void init() {
        try (Connection con = Db.getInstance().getConnection()) {
            if (con == null) return;

            try (Statement st = con.createStatement()) {
                st.executeUpdate(CREATE_FLIGHT_CATEGORIES);
                st.executeUpdate(CREATE_FLIGHTS);
                st.executeUpdate(CREATE_USERS);
                st.executeUpdate(CREATE_BOOKINGS);
                st.executeUpdate(CREATE_UNIQUE_SEAT_INDEX);

                st.executeUpdate(INSERT_CATEGORY_DOMESTIC);
                st.executeUpdate(INSERT_CATEGORY_INTERNATIONAL);
            }

        } catch (Exception e) {
            System.out.println("Init error: " + e.getMessage());
        }
    }
}
