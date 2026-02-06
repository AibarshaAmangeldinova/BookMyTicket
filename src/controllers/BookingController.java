package controllers;

import models.Booking;
import services.BookingService;

public class BookingController {
    private final BookingService bookingService = new BookingService();

    public Integer bookTicket(int flightId,
                              String passengerName,
                              String seatNumber,
                              String ticketClass,
                              String documentType,
                              String phone,
                              String documentNumber) {

        Booking b = new Booking();
        b.flightId = flightId;
        b.passengerName = passengerName;
        b.seatNumber = seatNumber;
        b.ticketClass = ticketClass;
        b.documentType = documentType;
        b.phone = phone;
        b.documentNumber = documentNumber;

        int bookingId = bookingService.bookTicket(b);
        return bookingId == -1 ? null : bookingId;
    }
}

