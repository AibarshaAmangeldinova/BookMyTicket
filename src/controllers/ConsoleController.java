package com.company.controllers;

import com.company.models.Booking;
import com.company.repositories.BookingRepository;
import com.company.repositories.FlightRepository;

import java.util.Scanner;

public class ConsoleController {

    private final FlightRepository flightRepo = new FlightRepository();
    private final BookingRepository bookingRepo = new BookingRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        flightRepo.showAllFlights();

        Booking booking = new Booking();

        System.out.print("Enter flight id: ");
        booking.flightId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Passenger name: ");
        booking.passengerName = scanner.nextLine();

        System.out.print("Seat number: ");
        booking.seatNumber = scanner.nextLine();

        System.out.print("Ticket class: ");
        booking.ticketClass = scanner.nextLine();

        System.out.print("Document type: ");
        booking.documentType = scanner.nextLine();

        bookingRepo.save(booking);
    }
}
