package data;

import utils.Console;

public class DatabaseInitializer {

    public void showHint() {
        Console.println("\n[DB INIT]");
        Console.println("This app expects tables: flight_categories, flights, users, staff_accounts, bookings.");
        Console.println(SqlConstants.SCHEMA_HINT);
    }
}
