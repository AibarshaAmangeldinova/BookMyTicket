package exceptions;

public class SeatAlreadyTakenException extends RuntimeException {
    public SeatAlreadyTakenException(String msg) {
        super(msg);
    }
}

