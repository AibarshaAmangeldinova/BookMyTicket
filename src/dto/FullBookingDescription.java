package dto;

public class FullBookingDescriptionDTO {

    public final int bookingId;
    public final String status;
    public final String createdAt;

    public final String passengerName;
    public final String phone;
    public final String documentType;
    public final String documentNumber;

    public final String origin;
    public final String destination;
    public final int price;
    public final String category;

    public final String seatNumber;
    public final String ticketClass;

    public FullBookingDescriptionDTO(
            int bookingId,
            String status,
            String createdAt,
            String passengerName,
            String phone,
            String documentType,
            String documentNumber,
            String origin,
            String destination,
            int price,
            String category,
            String seatNumber,
            String ticketClass
    ) {
        this.bookingId = bookingId;
        this.status = status;
        this.createdAt = createdAt;
        this.passengerName = passengerName;
        this.phone = phone;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.category = category;
        this.seatNumber = seatNumber;
        this.ticketClass = ticketClass;
    }
}
 }