package controllers;
import com.company.repositories.BookingRepository;
import com.company.repositories.Flight_Repository;

import java.util.Scanner;

public class ConsoleController {

    public void start() {
        Scanner sc = new Scanner(System.in);

        Flight_Repository flightRepo = new Flight_Repository();
        BookingRepository bookingRepo = new BookingRepository();

        while (true) {
            System.out.println("\n1. Show flights");
            System.out.println("2. Buy ticket");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            if (choice == 1) {
                flightRepo.showFlights();
            }

            if (choice == 2) {
                System.out.print("Enter flight id: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter passenger name: ");
                String name = sc.nextLine();

                bookingRepo.bookTicket(id, name);
            }

            if (choice == 0) {
                break;
            }
        }
    }
}

