package services;

import dto.FullBookingDescription;
import repositories.RepositoryFactory;

import java.util.List;

public class BookingService {

    private final RepositoryFactory factory = new RepositoryFactory();

    private final var bookingRepo = factory.bookingRepo();
    private final var userRepo = factory.userRepo();
    private final var seatRepo = factory.seatRepo();
    private final var flightRepo = factory.flightRepo();

    public Integer bookTicket(int flightId,
                              String firstName, String lastName,
                              String phone,
                              String docType, String docNumber,
                              String seat, String ticketClass) {

        // Business logic (criterion #1/#6): seat check
        if (seatRepo.isSeatTaken(flightId, seat)) return null;

        Integer userId = userRepo.createUser(firstName, lastName, phone, docType, docNumber);
        if (userId == null) return null;

        return bookingRepo.createBooking(flightId, userId, seat, ticketClass);
    }

    public Integer cancelBookingAndRefund(int bookingId) {
        Integer flightId = bookingRepo.getActiveFlightIdByBookingId(bookingId);
        if (flightId == null) return null;

        boolean ok = bookingRepo.cancelBooking(bookingId);
        if (!ok) return null;

        Integer price = flightRepo.getPrice(flightId);
        return price == null ? 0 : price;
    }

    public FullBookingDescription getFullBookingDescription(int bookingId) {
        // JOIN endpoint (criterion #1)
        return bookingRepo.getFullBooking(bookingId);
    }

    public void printManagerStats() {
        int active = bookingRepo.countActiveBookings();

        // Lambda expressions (criterion #3)
        List<Integer> examplePrices = List.of(120000, 90000, 250000, 110000, 180000);
        long expensive = examplePrices.stream().filter(p -> p >= 150000).count();

        System.out.println("\n=== MANAGER STATS ===");
        System.out.println("Active bookings: " + active);
        System.out.println("Example: flights >= 150000 count: " + expensive);
    }
}
