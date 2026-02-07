package services;

import repositories.CategoryRepository;
import repositories.RepositoryFactory;
import utils.Console;
import utils.Validator;

import java.util.List;

public class FlightService {
    private final Console console = new Console();
    private final RepositoryFactory factory = new RepositoryFactory();

    public void printFlightsTable() {
        List<String[]> rows = factory.flightRepo().getAllForTable();
        console.println("\nID | ORIGIN -> DESTINATION | CATEGORY | PRICE");
        for (String[] r : rows) {
            console.println(r[0] + " | " + r[1] + " -> " + r[2] + " | " + r[3] + " | " + r[4]);
        }
    }

    public void addFlightAdmin() {
        console.println("\n--- ADD FLIGHT (ADMIN) ---");
        String origin = console.readLine("Origin: ").trim();
        String dest = console.readLine("Destination: ").trim();
        Integer price = console.readInt("Price: ");
        String categoryName = console.readLine("Category (Domestic/International): ").trim();

        if (!Validator.validName(origin) || !Validator.validName(dest) || price == null || price <= 0) {
            console.println("Invalid data.");
            return;
        }

        CategoryRepository catRepo = factory.categoryRepo();
        Integer catId = catRepo.getCategoryIdByName(categoryName);

        boolean ok = factory.flightRepo().addFlight(origin, dest, price, catId);
        console.println(ok ? "Flight added." : "Failed.");
    }
}
