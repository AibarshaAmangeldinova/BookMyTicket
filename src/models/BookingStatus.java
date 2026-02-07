package models;

public enum BookingStatus {
    BOOKED,
    CANCELED;

    public static BookingStatus fromString(String value) {
        return BookingStatus.valueOf(value.toUpperCase());
    }
}
