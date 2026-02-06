package services;

import repositories.BookingRepository;
import repositories.RepositoryFactory;

import java.util.Arrays;
import java.util.List;

public class ReportService {
    private final BookingRepository bookingRepo = new RepositoryFactory().bookingRepo();

    public void printManagerStats() {
        int active = bookingRepo.countActiveBookings();

        // lambda expressions with stream
        List<Integer> demo = Arrays.asList(90000, 110000, 120000, 250000, 300000);
        long expensive = demo.stream().filter(p -> p >= 150000).count();

        System.out.println("\n=== MANAGER STATS ===");
        System.out.println("Active bookings: " + active);
        System.out.println("Lambda demo (prices >= 150000): " + expensive);
    }
}
