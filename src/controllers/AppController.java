package controllers;

import models.Role;
import security.AuthService;
import security.CurrentUser;
import services.BookingService;
import services.FlightService;
import services.StatsService;
import utils.Console;

public class AppController {

    private final Console console = new Console();
    private final AuthService authService = new AuthService();
    private final FlightService flightService = new FlightService();
    private final BookingService bookingService = new BookingService();
    private final StatsService statsService = new StatsService();

    public void start() {
        console.println("Welcome to BookMyTicket!");
        login();

        while (true) {
            printMenu();
            Integer opt = console.readInt("Choose option: ");
            if (opt == null) continue;

            if (opt == 0) {
                console.println("Bye!");
                return;
            }

            switch (opt) {
                case 1:
                    flightService.printFlightsTable();
                    break;

                case 2:
                    bookingService.bookTicketWithCashierDialog(); // внутри 0 cancel
                    break;

                case 3:
                    bookingService.cancelBookingWithRefund();
                    break;

                case 4:
                    bookingService.printFullBookingJoin();
                    break;

                case 5:
                    if (!CurrentUser.hasRole(Role.ADMIN)) {
                        console.println("Access denied: ADMIN only.");
                    } else {
                        flightService.addFlightAdmin();
                    }
                    break;

                case 6:
                    if (!CurrentUser.hasRole(Role.MANAGER)) {
                        console.println("Access denied: MANAGER only.");
                    } else {
                        statsService.printManagerStats();
                    }
                    break;

                default:
                    console.println("Unknown option.");
            }
        }
    }

    private void login() {
        console.println("\n--- LOGIN ---");
        console.println("Choose role to simulate secured endpoints:");
        console.println("1) CASHIER");
        console.println("2) MANAGER");
        console.println("3) ADMIN");

        while (true) {
            Integer r = console.readInt("Role: ");
            if (r == null) continue;
            Role role = (r == 1) ? Role.CASHIER : (r == 2) ? Role.MANAGER : (r == 3) ? Role.ADMIN : null;
            if (role == null) {
                console.println("Wrong role.");
                continue;
            }
            authService.loginAs(role);
            console.println("Logged in as: " + CurrentUser.getRole());
            break;
        }
    }

    private void printMenu() {
        console.println("\n=== MENU ===");
        console.println("1) Show flights");
        console.println("2) Book ticket (cashier dialog, 0 cancels)");
        console.println("3) Cancel booking + refund");
        console.println("4) Get full booking description (JOIN)");
        console.println("5) Add flight (ADMIN only)");
        console.println("6) Manager stats (MANAGER only)");
        console.println("0) Exit");
        console.println("\nRole: " + CurrentUser.getRole());
    }
}
