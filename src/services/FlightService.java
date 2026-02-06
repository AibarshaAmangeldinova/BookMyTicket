package services;

import repositories.FlightRepository;

public class FlightService {
    private final FlightRepository repo = new FlightRepository();

    public void showAllFlights() {
        repo.showAll();
    }

    public boolean flightExists(int flightId) {
        return repo.exists(flightId);
    }

    public Integer getPrice(int flightId) {
        return repo.getPrice(flightId);
    }
}
