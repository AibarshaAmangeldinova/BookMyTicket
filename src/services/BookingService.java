package services;

import models.Booking;
import repositories.BookingRepository;
import repositories.FlightRepository;

public class BookingService {

    private final BookingRepository bookingRepo = new BookingRepository();
    private final FlightRepository flightRepo = new FlightRepository();

    public int bookTicket(Booking b) {

        if (bookingRepo.isSeatTaken(b.flightId, b.seatNumber)) {
            System.out.println("❌ Sorry, this seat is already taken for this flight.");
            return -1;
        }

        int bookingId = bookingRepo.saveAndReturnId(b);

        if (bookingId == -1) {
            System.out.println("❌ Booking failed.");
        }

        return bookingId;
    }

    public void cancelBooking(int bookingId) {
        int flightId = bookingRepo.getFlightIdByBookingId(bookingId);

        if (flightId == -1) {
            System.out.println("❌ Booking not found.");
            return;
        }

        int price = flightRepo.getFlightPrice(flightId);
        boolean deleted = bookingRepo.deleteById(bookingId);

        if (deleted) {
            System.out.println("✅ Booking cancelled.");
            System.out.println("Refund: " + price + " KZT");
        } else {
            System.out.println("❌ Could not cancel booking.");
        }
    }
}
