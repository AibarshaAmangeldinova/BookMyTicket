package controllers;

import dto.README_CRITERIA;
import dto.README_SOLID;
import security.AuthService;
import security.CurrentUser;
import security.Role;
import services.BookingService;
import services.FlightService;
import services.HelpService;
import utils.Console;
import utils.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppController {

    private final AuthService auth = new AuthService();
    private final FlightService flightService = new FlightService();
    private final BookingService bookingService = new BookingService();
    private final HelpService helpService = new HelpService();

    private final README_SOLID solidDoc = new README_SOLID();
    private final README_CRITERIA criteriaDoc = new README_CRITERIA();

    public void start() {
        Console.println("=== BookMyTicket (Console) ===");
        if (!login()) return;

        // Lambdas (criterion #3): menu via Map<Integer, Runnable>
        Map<Integer, Runnable> menu = new LinkedHashMap<>();
        menu.put(1, this::showFlights);
        menu.put(2, this::buyTicketWithCashierDialog);
        menu.put(3, this::cancelBooking);
        menu.put(4, this::fullBookingJoin);
        menu.put(5, this::addFlightAdminOnly);
        menu.put(6, this::managerStatsManagerOnly);
        menu.put(7, solidDoc::runDemo);
        menu.put(8, criteriaDoc::runDemo);
        menu.put(0, () -> Console.println("Bye!"));

        while (true) {
            printMenu();
            Integer opt = Console.readInt("Choose option", true);
            if (opt == null) continue;

            Runnable action = menu.get(opt);
            if (action == null) {
                Console.println("Wrong option.");
                continue;
            }

            action.run();
            if (opt == 0) break;
        }
    }

    private boolean login() {
        Console.println("\n--- LOGIN ---");
        String username = Console.readString("Username", false);
        String password = Console.readString("Password", false);

        if (!auth.login(username, password)) {
            Console.println("❌ Login failed.");
            return false;
        }
        Console.println(" Logged in as: " + CurrentUser.username() + " (" + CurrentUser.role() + ")");
        return true;
    }

    private void printMenu() {
        Console.println("""
            \n=== MENU ===
            1. Show flights
            2. Buy ticket (cashier dialog)
            3. Cancel booking (refund)
            4. Get FULL booking description (JOIN)
            5. Add flight (ADMIN only)
            6. Manager stats (MANAGER only)
            7. Show SOLID explanation (code)
            8. Show criteria checklist (code)
            0. Exit
            """);
    }

    private void showFlights() {
        flightService.showFlightsTable();
    }

    private void buyTicketWithCashierDialog() {
        helpService.cashierHello();

        flightService.showFlightsTable();
        helpService.cashierAskChooseFlight();

        Integer flightId = Console.readInt("Enter flight id", true);
        if (flightId == null) { helpService.cashierCancelled(); return; }

        if (!flightService.flightExists(flightId)) {
            helpService.cashierSayFlightNotFound();
            return;
        }

        helpService.cashierAskPassengerIntro();

        String firstName = Console.readString("First name", true);
        if (firstName == null) { helpService.cashierCancelled(); return; }

        String lastName = Console.readString("Last name", true);
        if (lastName == null) { helpService.cashierCancelled(); return; }

        String phone = Console.readString("Phone (+7...)", true);
        if (phone == null) { helpService.cashierCancelled(); return; }

        String docType = Console.readString("Document type (PASSPORT/ID_CARD)", true);
        if (docType == null) { helpService.cashierCancelled(); return; }

        String docNumber = Console.readString("Document number", true);
        if (docNumber == null) { helpService.cashierCancelled(); return; }

        helpService.cashierAskSeatAndClass();

        String seat = Console.readString("Seat number (e.g. 8E)", true);
        if (seat == null) { helpService.cashierCancelled(); return; }

        String ticketClass = Console.readString("Ticket class (ECONOMY/BUSINESS)", true);
        if (ticketClass == null) { helpService.cashierCancelled(); return; }

        // Validation (criterion #6)
        String err = Validator.validatePassenger(firstName, lastName, phone, docType, docNumber, seat, ticketClass);
        if (err != null) {
            helpService.cashierValidationError(err);
            return;
        }

        helpService.cashierProcessing();

        Integer bookingId = bookingService.bookTicket(
                flightId, firstName, lastName, phone, docType, docNumber, seat, ticketClass
        );

        if (bookingId == null) {
            helpService.cashierSeatTaken();
            return;
        }

        helpService.cashierSuccess(bookingId);
    }

    private void cancelBooking() {
        helpService.cashierCancelIntro();

        Integer bookingId = Console.readInt("Enter booking id", true);
        if (bookingId == null) { helpService.cashierCancelled(); return; }

        Integer refund = bookingService.cancelBookingAndRefund(bookingId);
        if (refund == null) {
            helpService.cashierCancelNotFound();
            return;
        }
        helpService.cashierCancelSuccess(refund);
    }

    private void fullBookingJoin() {
        Integer bookingId = Console.readInt("Enter booking id", true);
        if (bookingId == null) return;

        var dto = bookingService.getFullBookingDescription(bookingId);
        if (dto == null) {
            Console.println(" Not found.");
            return;
        }

        Console.println("\n=== FULL BOOKING (JOIN) ===");
        Console.println(dto.toPrettyString());
    }

    private void addFlightAdminOnly() {
        // Role management (criterion #5)
        if (!CurrentUser.hasRole(Role.ADMIN)) {
            Console.println(" Access denied. ADMIN only.");
            return;
        }

        String origin = Console.readString("Origin", false);
        String destination = Console.readString("Destination", false);
        Integer price = Console.readInt("Price", false);
        String category = Console.readString("Category (Domestic/International/Budget)", false);

        if (price == null || price <= 0) {
            Console.println(" Price must be > 0");
            return;
        }

        boolean ok = flightService.addFlight(origin, destination, price, category);
        Console.println(ok ? " Flight added." : " Could not add flight (check category name).");
    }

    private void managerStatsManagerOnly() {
        if (!CurrentUser.hasRole(Role.MANAGER)) {
            Console.println(" Access denied. MANAGER only.");
            return;
        }
        bookingService.printManagerStats();
    }
}
