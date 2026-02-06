package dto;

public class FullBookingDescription {
    public int bookingId;
    public String status;
    public String createdAt;

    public String passengerName;
    public String phone;
    public String documentType;
    public String documentNumber;

    public String origin;
    public String destination;
    public int price;
    public String category;

    public String seatNumber;
    public String ticketClass;

    public String toPrettyString() {
        return """
            Booking ID: %d
            Status: %s
            Created: %s

            Passenger: %s
            Phone: %s
            Document: %s %s

            Flight: %s -> %s
            Category: %s
            Seat: %s
            Class: %s
            Price: %d
            """.formatted(
                bookingId, status, createdAt,
                passengerName, phone, documentType, documentNumber,
                origin, destination, category,
                seatNumber, ticketClass, price
        );
    }
}

