package dto;

import utils.Console;

public class README_CRITERIA {

    public void runDemo() {
        Console.println("\n=== CRITERIA CHECKLIST (CODE OUTPUT) ===");
        Console.println(item1());
        Console.println(item2());
        Console.println(item3());
        Console.println(item4());
        Console.println(item5());
        Console.println(item6());
        Console.println(item7());
    }

    private String item1() {
        return """
        1) JOINs:
        - BookingRepository.getFullBooking(bookingId)
          joins bookings + users + flights + flight_categories in ONE query.
        """;
    }

    private String item2() {
        return """
        2) Design Patterns:
        - Singleton: data.Db.getInstance()
        - Factory: repositories.RepositoryFactory (creates repositories)
        """;
    }

    private String item3() {
        return """
        3) Lambda expressions:
        - AppController menu is Map<Integer, Runnable> (method references)
        - BookingService uses stream().filter(p -> p >= 150000)
        """;
    }

    private String item4() {
        return """
        4) SOLID:
        - Controller/Service/Repository separation makes it easy to explain SOLID.
        - You can run menu option 7 for SOLID explanation output.
        """;
    }

    private String item5() {
        return """
        5) Role Management (secured endpoints):
        - ADMIN only: Add flight
        - MANAGER only: Manager stats
        - Auth uses staff_accounts table + CurrentUser role checks.
        """;
    }

    private String item6() {
        return """
        6) Data validation:
        - utils.Validator checks phone, seat format, ticket class, document type, document number length.
        """;
    }

    private String item7() {
        return """
        7) Categories:
        - flight_categories table + flights.category_id
        - Flight list prints category via LEFT JOIN.
        """;
    }
}
