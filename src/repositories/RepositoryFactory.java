package repositories;

public class RepositoryFactory {
    public FlightRepository flightRepo() { return new FlightRepository(); }
    public BookingRepository bookingRepo() { return new BookingRepository(); }
    public SeatRepository seatRepo() { return new SeatRepository(); }
    public UserRepository userRepo() { return new UserRepository(); }
    public CategoryRepository categoryRepo() { return new CategoryRepository(); }
    public StaffRepository staffRepo() { return new StaffRepository(); }
}

