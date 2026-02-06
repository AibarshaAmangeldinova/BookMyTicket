package controllers;

import services.BookingService;
import services.FlightService;
import ui.CashierDialog;
import utils.InputUtils;
import utils.PrinterUtils;

import java.util.Scanner;

public class ConsoleController {
    private final Scanner scanner = new Scanner(System.in);

    private final FlightController flightController = new FlightController();
    private final BookingController bookingController = new BookingController();
    private final UserController userController = new UserController();

    private final FlightService flightService = new FlightService();
    private final BookingService bookingService = new BookingService();

    private final CashierDialog cashier = new CashierDialog();

    public void start() {
        while (true) {
            PrinterUtils.printMenu();

            Integer choice = InputUtils.readInt(scanner, "Choose option", true);
            if (choice == null) continue;

            switch (choice) {
                case 1 -> flightController.showFlights();
                case 2 -> buyFlow();
                case 3 -> cancelFlow();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Wrong option.");
            }
        }
    }

    private void buyFlow() {
        cashier.greet();
        flightController.showFlights();
        cashier.explainFlights();

        Integer flightId = InputUtils.readInt(scanner, "Enter flight id", true);
        if (flightId == null) { cashier.cancelledByUser(); return; }

        if (!flightService.flightExists(flightId)) {
            System.out.println("❌ Flight not found.");
            return;
        }

        cashier.askPersonalData();

        String firstName = InputUtils.readString(scanner, "First name", true);
        if (firstName == null) { cashier.cancelledByUser(); return; }

        String lastName = InputUtils.readString(scanner, "Last name", true);
        if (lastName == null) { cashier.cancelledByUser(); return; }

        String phone = InputUtils.readString(scanner, "Phone", true);
        if (phone == null) { cashier.cancelledByUser(); return; }

        String documentType = InputUtils.readString(scanner, "Document type (PASSPORT / ID_CARD)", true);
        if (documentType == null) { cashier.cancelledByUser(); return; }

        String documentNumber = InputUtils.readString(scanner, "Document number", true);
        if (documentNumber == null) { cashier.cancelledByUser(); return; }

        cashier.askSeatAndClass();

        String seatNumber = InputUtils.readString(scanner, "Seat number (e.g. 8E)", true);
        if (seatNumber == null) { cashier.cancelledByUser(); return; }

        String ticketClass = InputUtils.readString(scanner, "Ticket class (ECONOMY / BUSINESS)", true);
        if (ticketClass == null) { cashier.cancelledByUser(); return; }

        int userId = userController.registerUser(firstName, lastName, phone, documentType, documentNumber);
        if (userId == -1) {
            System.out.println("❌ Could not create user.");
            return;
        }

        String passengerName = firstName + " " + lastName;

        cashier.processing();
        Integer bookingId = bookingController.bookTicket(flightId, passengerName, seatNumber, ticketClass, documentType, phone, documentNumber);

        if (bookingId == null) {
            cashier.seatTaken();
            return;
        }

        cashier.success(bookingId);
    }

    private void cancelFlow() {
        cashier.cancelBookingIntro();
        Integer bookingId = InputUtils.readInt(scanner, "Enter booking id", true);
        if (bookingId == null) { cashier.cancelledByUser(); return; }

        Integer refund = bookingService.cancelBookingAndGetRefund(bookingId);
        if (refund == null) {
            cashier.cancelNotFound();
        } else {
            cashier.cancelSuccess(refund);
        }
    }
}
