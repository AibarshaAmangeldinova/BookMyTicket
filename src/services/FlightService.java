package services;

import repositories.FlightRepository;
import repositories.RepositoryFactory;
import utils.Console;

import java.util.Comparator;
import java.util.List;

public class FlightService {
    private final FlightRepository flightRepo = new RepositoryFactory().flightRepo();

    public void printFlights() {
        List<String[]> rows = flightRepo.getFlightsForTable();

        // lambda: sort by price (business-friendly)
        rows.sort(Comparator.comparingInt(r -> Integer.parseInt(r[4])));

        Console.println("\nID | ORIGIN -> DESTINATION | CATEGORY | PRICE");
        for (String[] r : rows) {
            Console.println(r[0] + " | " + r[1] + " -> " + r[2] + " | " + r[3] + " | " + r[4]);
        }
        Console.println("");
    }

    public boolean addFlight(String origin, String destination, int price, String categoryName) {
        return flightRepo.addFlight(origin, destination, price, categoryName);
    }
}
