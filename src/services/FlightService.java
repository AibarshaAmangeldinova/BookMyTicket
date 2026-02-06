package services;

import repositories.FlightRepository;
import repositories.RepositoryFactory;
import utils.TextTable;

public class FlightService {

    private final FlightRepository flightRepo;

    public FlightService() {
        // Factory pattern (criterion #2)
        this.flightRepo = new RepositoryFactory().flightRepo();
    }

    public void showFlightsTable() {
        var rows = flightRepo.getAllForTable();
        TextTable.print(
                new String[]{"ID", "Origin", "Destination", "Category", "Price"},
                rows
        );
    }

    public boolean flightExists(int flightId) {
        return flightRepo.exists(flightId);
    }

    public Integer getPrice(int flightId) {
        return flightRepo.getPrice(flightId);
    }

    public boolean addFlight(String origin, String destination, int price, String categoryName) {
        return flightRepo.addFlight(origin, destination, price, categoryName);
    }
}

