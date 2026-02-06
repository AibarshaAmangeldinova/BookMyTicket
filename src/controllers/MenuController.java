package controllers;

import security.CurrentUser;
import security.Role;
import services.BookingService;
import services.FlightService;
import services.ReportService;
import utils.Console;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuController {

    private final FlightService flightService = new FlightService();
    private final BookingService bookingService = new BookingService();
    private final ReportService reportService = new ReportService();
    private final CashierDialog cashier = new CashierDialog();

    public void run() {
        Map<Integer, Runnable> actions = new LinkedHashMap<>();
        actions.put(1, this::showFlights);
        actions.put(2, this::bookTicketDialog);
        actions.put(3, this::cancelBookingDialog);
        actions.put(4, this::fullJoinDialog);
        actions.put(5, this::adminAddFlight);      // ADMIN only
        actions.put(6, this::managerStats);        // MANAGER only
        actions.put(7, this::solidExplain);
        actions.put(0, () -> Console.println("Goodbye!"));

        while (true) {
            printMenu();
            Integer opt = Console.readInt("Choose option");
            if (opt == null) continue;

            Runnable r = actions.get(opt);
            if (r == null) {
                Console.println("Unknown option.");
                continue;
            }

            r.run();
            if (opt == 0) return;
        }
    }

    private void printMenu() {
        Console.println("""
            \n=== MENU ===
            1) Show flights
            2) Book ticket (cashier dialog, 0 cancels)
            3) Cancel booking + refund
            4) Get full booking description (JOIN)
            5) Add flight (ADMIN only)
            6) Manager stats (MANAGER only)
            7) SOLID explanation
            0) Exit
            """);
        Console.println("Role: " + CurrentUser.getRole());
    }

    private void showFlights() {
        flightService.printFlights();
    }

    private void bookTicketDialog() {
        if (!CurrentUser.hasAny(Role.CASHIER, Role.MANAGER, Role.ADMIN)) {
            Console.println(" Access denied (need CASHIER/MANAGER/ADMIN).");
            return;
        }

        cashier.hello();
        flightService.printFlights();

        Integer flightId = Console.readInt("Cashier: Enter flight ID (0=cancel)");
        if (flightId == null) { cashier.cancelled(); return; }

        cashier.passengerIntro();
        String first = Console.readString("First name (0=cancel)", true);
        if (first == null) { cashier.cancelled(); return; }

        String last = Console.readString("Last name (0=cancel)", true);
        if (last == null) { cashier.cancelled(); return; }

        String phone = Console.readString("Phone (+7700...) (0=cancel)", true);
        if (phone == null) { cashier.cancelled(); return; }

        String docType = Console.readString("Document type (PASSPORT/ID_CARD) (0=cancel)", true);
        if (docType == null) { cashier.cancelled(); return; }

        String docNum = Console.readString("Document number (0=cancel)", true);
        if (docNum == null) { cashier.cancelled(); return; }

        cashier.seatIntro();
        String seat = Console.readString("Seat number (8E, 12A) (0=cancel)", true);
        if (seat == null) { cashier.cancelled(); return; }

        String cls = Console.readString("Ticket class (ECONOMY/BUSINESS) (0=cancel)", true);
        if (cls == null) { cashier.cancelled(); return; }

        cashier.processing();

        Integer bookingId = bookingService.bookTicket(
                flightId, first, last, phone, docType, docNum, seat, cls
        );

        if (bookingId == null) cashier.fail();
        else {
            cashier.success(bookingId);
            bookingService.printFullBooking(bookingId);
        }
    }

    private void cancelBookingDialog() {
        Integer bookingId = Console.readInt("Enter booking id to cancel (0=back)");
        if (bookingId == null) return;

        Integer refund = bookingService.cancelBookingRefund(bookingId);
        if (refund == null) Console.println(" Cancel failed (not found / already canceled).");
        else Console.println(" Canceled. Refund: " + refund + " KZT");
    }

    private void fullJoinDialog() {
        Integer bookingId = Console.readInt("Enter booking id (0=back)");
        if (bookingId == null) return;

        bookingService.printFullBooking(bookingId);
    }

    private void adminAddFlight() {
        if (!CurrentUser.hasAny(Role.ADMIN)) {
            Console.println(" Access denied (ADMIN only).");
            return;
        }

        String origin = Console.readString("Origin", false);
        String destination = Console.readString("Destination", false);
        Integer price = Console.readInt("Price");
        String category = Console.readString("Category (Domestic/International/Budget)", false);

        boolean ok = flightService.addFlight(origin, destination, price, category);
        Console.println(ok ? " Flight added." : " Failed (check category name).");
    }

    private void managerStats() {
        if (!CurrentUser.hasAny(Role.MANAGER)) {
            Console.println(" Access denied (MANAGER only).");
            return;
        }
        reportService.printManagerStats();
    }

    private void solidExplain() {
        Console.println("\n=== SOLID (how to explain) ===");
        Console.println("S: Each class has one job: Controller(UI), Service(business logic), Repository(DB).");
        Console.println("O: Easy to add new menu features without rewriting DB code.");
        Console.println("L: Enums keep correct states (TicketClass/DocumentType) so logic stays valid.");
        Console.println("I: Repos separated: FlightRepo/BookingRepo/UserRepo/SeatRepo.");
        Console.println("D: Controller depends on Service, Service depends on Repos (not on raw SQL in UI).");
    }
}
