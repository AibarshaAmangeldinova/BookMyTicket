package controllers;

import models.Booking;
import repositories.FlightRepository;
import services.BookingService;
import ui.CashierDialog;
import utils.InputUtils;

import java.util.Scanner;

public class ConsoleController {

    private final Scanner scanner = new Scanner(System.in);

    private final FlightRepository flightRepo = new FlightRepository();
    private final BookingService bookingService = new BookingService();
    private final CashierDialog cashier = new CashierDialog();

    public void start() {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Show flights");
            System.out.println("2. Buy ticket");
            System.out.println("3. Cancel booking");
            System.out.println("0. Exit");

            Integer choice = InputUtils.readIntOrCancel(scanner, "Choose option");
            if (choice == null) continue;

            if (choice == 1) {
                flightRepo.showAllFlights();
            } else if (choice == 2) {
                buyTicketFlow();
            } else if (choice == 3) {
                cancelFlow();
            } else if (choice == 0) {
                System.out.println("Bye!");
                break;
            }
        }
    }

    private void buyTicketFlow() {
        cashier.hello();

        flightRepo.showAllFlights();
        cashier.askFlight();

        Integer flightId = InputUtils.readIntOrCancel(scanner, "Flight id");
        if (flightId == null) { cashier.cancelledByUser(); return; }

        String passengerName = InputUtils.readStringOrCancel(scanner, "Passenger name");
        if (passengerName == null) { cashier.cancelledByUser(); return; }

        String phone = InputUtils.readStringOrCancel(scanner, "Phone");
        if (phone == null) { cashier.cancelledByUser(); return; }

        String documentType = InputUtils.readStringOrCancel(scanner, "Document type");
        if (documentType == null) { cashier.cancelledByUser(); return; }

        String documentNumber = InputUtils.readStringOrCancel(scanner, "Document number");
        if (documentNumber == null) { cashier.cancelledByUser(); return; }

        String seatNumber = InputUtils.readStringOrCancel(scanner, "Seat number");
        if (seatNumber == null) { cashier.cancelledByUser(); return; }

        String ticketClass = InputUtils.readStringOrCancel(scanner, "Ticket class");
        if (ticketClass == null) { cashier.cancelledByUser(); return; }

        Booking b = new Booking();
        b.flightId = flightId;
        b.passengerName = passengerName;
        b.seatNumber = seatNumber;
        b.ticketClass = ticketClass;
        b.documentType = documentType;
        b.phone = phone;
        b.documentNumber = documentNumber;


        cashier.confirm();
        int bookingId = bookingService.bookTicket(b);

        if (bookingId != -1) cashier.done(bookingId);
    }

    private void cancelFlow() {
        Integer bookingId = InputUtils.readIntOrCancel(scanner, "Booking id");
        if (bookingId == null) { cashier.cancelledByUser(); return; }

        bookingService.cancelBooking(bookingId);
    }
}

