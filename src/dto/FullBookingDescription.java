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

    public String pretty() {
        return "\n=== FULL BOOKING (JOIN) ===\n" +
                "Booking ID: " + bookingId + "\n" +
                "Status: " + status + "\n" +
                "Created: " + createdAt + "\n" +
                "Passenger: " + passengerName + "\n" +
                "Phone: " + phone + "\n" +
                "Document: " + documentType + " " + documentNumber + "\n" +
                "Flight: " + origin + " -> " + destination + "\n" +
                "Category: " + category + "\n" +
                "Seat: " + seatNumber + "\n" +
                "Class: " + ticketClass + "\n" +
                "Price: " + price + "\n" +
                "==========================\n";
    }
}
