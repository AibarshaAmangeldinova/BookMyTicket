package models;

public class Booking {
    private int flightId;
    private String passengerName;
    private String seatNumber;
    private String ticketClass;
    private String documentType;

    public Booking() {}

    public Booking(int flightId, String passengerName, String seatNumber, String ticketClass, String documentType) {
        setFlightId(flightId);
        setPassengerName(passengerName);
        setSeatNumber(seatNumber);
        setTicketClass(ticketClass);
        setDocumentType(documentType);
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
