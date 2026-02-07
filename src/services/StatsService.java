package services;

import repositories.RepositoryFactory;
import utils.Console;

import java.util.Arrays;
import java.util.List;

public class StatsService {
    private final Console console = new Console();
    private final RepositoryFactory factory = new RepositoryFactory();

    public void printManagerStats() {
        console.println("\n--- MANAGER STATS ---");

        int active = factory.bookingRepo().countActiveBookings();
        console.println("Active bookings (BOOKED): " + active);

        // lambda example (простая демонстрация)
        List<Integer> prices = Arrays.asList(120000, 90000, 250000, 18000);
        long expensive = prices.stream().filter(p -> p > 100000).count(); // lambda
        console.println("Lambda demo: sample expensive flights count (>100000): " + expensive);
    }
}

