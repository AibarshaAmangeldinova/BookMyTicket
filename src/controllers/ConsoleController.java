package controllers;

import com.company.repositories.BookingRepository;
import com.company.repositories.Flight_Repository;
import java.util.Scanner;

public class ConsoleController {

    public void start() {
        Scanner scanner = new Scanner(System.in);

        Flight_Repository flightRepository = new Flight_Repository();
        BookingRepository bookingRepository = new BookingRepository();

        boolean running = true;

        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    flightRepository.showFlights();
                    break;

                case 2:
                    System.out.print("Enter flight id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();

                    bookingRepository.bookTicket(id, name);
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n1. Show flights");
        System.out.println("2. Buy ticket");
        System.out.println("0. Exit");
    }
}
