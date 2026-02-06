package controllers;

import models.Booking;
import repositories.FlightRepository;
import services.BookingService;
import ui.CashierDialog;
import utils.InputUtils;

import java.util.Scanner;

public class ConsoleController {

    private final Scanner scanner = new Scanner(System.in);

    private final FlightRepository flightRepository = new FlightRepository();
    private final BookingService bookingService = new BookingService();
    private final CashierDialog cashierDialog = new CashierDialog();

    public void start() {

        while (true) {
            printMenu();

            Integer option = InputUtils.readIntOrCancel(scanner, "Choose option");
            if (option == null) continue;

            switch (option) {
                case 1 -> flightRepository.showAllFlights();
                case 2 -> buyTicketFlow();
                case 3 -> cancelFlow();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Wrong option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Show flights");
        System.out.println("2. Buy ticket");
        System.out.println("3. Cancel booking");
        System.out.println("0. Exit");
    }

    private void buyTicketFlow() {

        cashierDialog.hello();

        flightRepository.showAllFlights();
        cashierDialog.askFlight();

        Integer flightId = InputUtils.readIntOrCancel(scanner, "Flight id");
        if (flightId == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String firstName = InputUtils.readStringOrCancel(scanner, "First name");
        if (firstName == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String lastName = InputUtils.readStringOrCancel(scanner, "Last name");
        if (lastName == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String phone = InputUtils.readStringOrCancel(scanner, "Phone");
        if (phone == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String documentType = InputUtils.readStringOrCancel(scanner, "Document type");
        if (documentType == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String documentNumber = InputUtils.readStringOrCancel(scanner, "Document number");
        if (documentNumber == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String seatNumber = InputUtils.readStringOrCancel(scanner, "Seat number");
        if (seatNumber == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        String ticketClass = InputUtils.readStringOrCancel(scanner, "Ticket class");
        if (ticketClass == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        Booking booking = new Booking();
        booking.flightId = flightId;
        booking.firstName = firstName;
        booking.lastName = lastName;
        booking.phone = phone;
        booking.documentType = documentType;
        booking.documentNumber = documentNumber;
        booking.seatNumber = seatNumber;
        booking.ticketClass = ticketClass;

        cashierDialog.confirm();

        int bookingId = bookingService.bookTicket(booking);

        if (bookingId != -1) {
            cashierDialog.done(bookingId);
        }
    }

    private void cancelFlow() {

        Integer bookingId = InputUtils.readIntOrCancel(scanner, "Booking id");

        if (bookingId == null) {
            cashierDialog.cancelledByUser();
            return;
        }

        bookingService.cancelBooking(bookingId);
    }
}
