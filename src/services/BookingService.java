package services;

import models.Booking;
import repositories.BookingRepository;
import repositories.SeatRepository;

public class BookingService {

    private final BookingRepository bookingRepo = new BookingRepository();
    private final SeatRepository seatRepo = new SeatRepository();
    private final FlightService flightService = new FlightService();

    public int bookTicket(Booking b) {
        if (seatRepo.isSeatTaken(b.flightId, b.seatNumber)) {
            return -1;
        }
        return bookingRepo.saveAndReturnId(b);
    }

    public Integer cancelBookingAndGetRefund(int bookingId) {
        Integer flightId = bookingRepo.getFlightIdByBookingId(bookingId);
        if (flightId == null) return null;

        Integer price = flightService.getPrice(flightId);
        if (price == null) price = 0;

        boolean deleted = bookingRepo.deleteById(bookingId);
        if (!deleted) return null;

        return price; // refund = price
    }
}
