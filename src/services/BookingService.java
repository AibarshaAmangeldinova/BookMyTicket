package services;

import dto.FullBookingDescription;
import repositories.RepositoryFactory;
import utils.Console;
import utils.Validator;

public class BookingService {

    private final Console console = new Console();
    private final RepositoryFactory factory = new RepositoryFactory();

    public void bookTicketWithCashierDialog() {
        console.println("\nCashier: Hello! Welcome to BookMyTicket.");
        console.println("Cashier: I can help you book a flight. (Type 0 anytime to cancel)");

        Integer flightId = console.readIntCancelable("Cashier: Please enter flight id (0 = cancel): ");
        if (flightId == null) { console.println("Cashier: Okay, canceled."); return; }

        if (!factory.flightRepo().exists(flightId)) {
            console.println("Cashier: Sorry, this flight does not exist.");
            return;
        }

        String firstName = console.readLineCancelable("Cashier: Passenger first name (0 = cancel): ");
        if (firstName == null || !Validator.validName(firstName)) { console.println("Cashier: Canceled / invalid."); return; }

        String lastName = console.readLineCancelable("Cashier: Passenger last name (0 = cancel): ");
        if (lastName == null || !Validator.validName(lastName)) { console.println("Cashier: Canceled / invalid."); return; }

        String phone = console.readLineCancelable("Cashier: Phone number (0 = cancel): ");
        if (phone == null || !Validator.validPhone(phone)) { console.println("Cashier: Canceled / invalid phone."); return; }

        String docType = console.readLineCancelable("Cashier: Document type (PASSPORT/ID_CARD) (0 = cancel): ");
        if (docType == null || !Validator.validDocType(docType)) { console.println("Cashier: Canceled / invalid doc type."); return; }

        String docNumber = console.readLineCancelable("Cashier: Document number (0 = cancel): ");
        if (docNumber == null || !Validator.validDocNumber(docNumber)) { console.println("Cashier: Canceled / invalid doc number."); return; }

        String seat = console.readLineCancelable("Cashier: Seat number (e.g. 8E) (0 = cancel): ");
        if (seat == null || !Validator.validSeat(seat)) { console.println("Cashier: Canceled / invalid seat."); return; }

        // проверка места (business logic)
        if (factory.seatRepo().isSeatTaken(flightId, seat)) {
            console.println("Cashier: Sorry, this seat is already taken for this flight.");
            return;
        }

        String ticketClass = console.readLineCancelable("Cashier: Ticket class (ECONOMY/BUSINESS) (0 = cancel): ");
        if (ticketClass == null || !Validator.validTicketClass(ticketClass)) { console.println("Cashier: Canceled / invalid class."); return; }

        console.println("Cashier: Great! I am booking your ticket now...");
        Integer userId = factory.userRepo().createUser(firstName, lastName, phone, docType, docNumber);
        if (userId == null) {
            console.println("Cashier: Sorry, could not create user.");
            return;
        }

        String passengerName = firstName.trim() + " " + lastName.trim();

        Integer bookingId = factory.bookingRepo().createBooking(
                flightId, userId, passengerName,
                seat, ticketClass, docType, phone, docNumber
        );

        if (bookingId == null) {
            console.println("Cashier: Booking failed.");
            return;
        }

        console.println("Cashier: Done! Your booking id is: " + bookingId);
        console.println("Cashier: Here is your full booking receipt:\n");

        FullBookingDescription d = factory.bookingRepo().getFullBooking(bookingId);
        printReceipt(d);
    }

    public void cancelBookingWithRefund() {
        console.println("\n--- CANCEL BOOKING ---");
        Integer bookingId = console.readInt("Enter booking id: ");
        if (bookingId == null) return;

        Integer flightId = factory.bookingRepo().getActiveFlightIdByBookingId(bookingId);
        if (flightId == null) {
            console.println("No active booking found.");
            return;
        }

        Integer price = factory.flightRepo().getPrice(flightId);
        if (price == null) price = 0;

        boolean ok = factory.bookingRepo().cancelBooking(bookingId);
        if (!ok) {
            console.println("Cancel failed.");
            return;
        }

        int refund = (int)Math.round(price * 0.9); // 90% refund
        console.println("Canceled successfully. Refund: " + refund);
    }

    public void printFullBookingJoin() {
        Integer bookingId = console.readInt("Enter booking id: ");
        if (bookingId == null) return;

        FullBookingDescription d = factory.bookingRepo().getFullBooking(bookingId);
        if (d == null) {
            console.println("Not found.");
            return;
        }
        printReceipt(d);
    }

    private void printReceipt(FullBookingDescription d) {
        if (d == null) {
            console.println("Receipt is empty.");
            return;
        }
        console.println("=== BOOKING RECEIPT ===");
        console.println("Booking: #" + d.bookingId + " | Status: " + d.status + " | " + d.createdAt);
        console.println("Passenger: " + d.passengerName);
        console.println("Phone: " + d.phone);
        console.println("Document: " + d.documentType + " " + d.documentNumber);
        console.println("Flight: " + d.origin + " -> " + d.destination + " | Category: " + d.category);
        console.println("Seat: " + d.seatNumber + " | Class: " + d.ticketClass);
        console.println("Price: " + d.price);
        console.println("=======================");
    }
}
