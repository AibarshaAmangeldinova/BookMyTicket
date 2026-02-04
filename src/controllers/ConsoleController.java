package controllers;

import models.Booking;
import models.Flight;
import repositories.BookingRepository;
import repositories.FlightRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private final FlightRepository flightRepo = new FlightRepository();
    private final BookingRepository bookingRepo = new BookingRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        List<Flight> flights = flightRepo.getAllFlights();
        for (Flight flight : flights) {
            System.out.println(
                    flight.getId() + " | " +
                            flight.getFromCity() + " -> " +
                            flight.getToCity() + " | " +
                            flight.getPrice()
            );
        }

        Booking booking = new Booking();

        System.out.print("Enter flight id: ");
        booking.setFlightId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Passenger name: ");
        booking.setPassengerName(scanner.nextLine());

        System.out.print("Seat number: ");
        booking.setSeatNumber(scanner.nextLine());

        System.out.print("Ticket class: ");
        booking.setTicketClass(scanner.nextLine());

        System.out.print("Document type: ");
        booking.setDocumentType(scanner.nextLine());

        bookingRepo.save(booking);
    }
}
