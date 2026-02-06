package services;

import dto.BookingRequest;
import dto.FullBookingDescription;
import models.DocumentType;
import models.TicketClass;
import repositories.*;

public class BookingService {

    private final RepositoryFactory factory = new RepositoryFactory();
    private final FlightRepository flightRepo = factory.flightRepo();
    private final SeatRepository seatRepo = factory.seatRepo();
    private final UserRepository userRepo = factory.userRepo();
    private final BookingRepository bookingRepo = factory.bookingRepo();

    public Integer bookTicket(int flightId,
                              String firstName, String lastName,
                              String phone,
                              String docType, String docNumber,
                              String seat,
                              String ticketClass) {

        BookingRequest r = new BookingRequest();
        r.flightId = flightId;
        r.firstName = firstName;
        r.lastName = lastName;
        r.phone = phone;
        r.documentType = docType;
        r.documentNumber = docNumber;
        r.seatNumber = seat;
        r.ticketClass = ticketClass;

        String err = validate(r);
        if (err != null) {
            System.out.println(" Validation error: " + err);
            return null;
        }

        if (!flightRepo.exists(flightId)) {
            System.out.println(" Flight not found.");
            return null;
        }

        if (seatRepo.isSeatTaken(flightId, r.seatNumber)) {
            System.out.println(" Seat already taken for this flight.");
            return null;
        }

        Integer userId = userRepo.createUser(r.firstName, r.lastName, r.phone, r.documentType, r.documentNumber);
        if (userId == null) return null;

        String passengerName = r.firstName + " " + r.lastName;

        return bookingRepo.createBooking(
                flightId, userId, passengerName,
                r.seatNumber, r.ticketClass,
                r.documentType, r.phone, r.documentNumber
        );
    }

    // business logic: refund 90%
    public Integer cancelBookingRefund(int bookingId) {
        Integer flightId = bookingRepo.getFlightIdIfBooked(bookingId);
        if (flightId == null) return null;

        Integer price = flightRepo.getPrice(flightId);
        if (price == null) price = 0;

        boolean ok = bookingRepo.cancelBooking(bookingId);
        if (!ok) return null;

        return (int)Math.round(price * 0.90);
    }

    public void printFullBooking(int bookingId) {
        FullBookingDescription d = flightRepo.getFullBookingDescription(bookingId);
        if (d == null) {
            System.out.println("Not found.");
            return;
        }
        System.out.println(d.pretty());
    }

    private String validate(BookingRequest r) {
        if (r.flightId <= 0) return "flightId must be > 0";
        if (r.firstName == null || r.firstName.trim().length() < 2) return "firstName too short";
        if (r.lastName == null || r.lastName.trim().length() < 2) return "lastName too short";

        if (r.phone == null || !r.phone.trim().matches("^\\+?\\d{10,15}$"))
            return "phone must be 10-15 digits (may start with +)";

        String dt = r.documentType == null ? "" : r.documentType.trim().toUpperCase();
        if (!("PASSPORT".equals(dt) || "ID_CARD".equals(dt))) return "documentType must be PASSPORT or ID_CARD";

        if (r.documentNumber == null || r.documentNumber.trim().length() < 4) return "documentNumber too short";

        String seat = r.seatNumber == null ? "" : r.seatNumber.trim().toUpperCase();
        if (!seat.matches("^\\d{1,2}[A-F]$")) return "seat format must be like 8E or 12A";

        String tc = r.ticketClass == null ? "" : r.ticketClass.trim().toUpperCase();
        if (!("ECONOMY".equals(tc) || "BUSINESS".equals(tc))) return "ticketClass must be ECONOMY or BUSINESS";

        // normalize
        r.firstName = r.firstName.trim();
        r.lastName = r.lastName.trim();
        r.phone = r.phone.trim();
        r.documentType = dt;
        r.ticketClass = tc;
        r.seatNumber = seat;

        // enum safety
        DocumentType.valueOf(dt);
        TicketClass.valueOf(tc);

        return null;
    }
}
