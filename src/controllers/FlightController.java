package controllers;

import services.FlightService;

public class FlightController {
    private final FlightService flightService = new FlightService();

    public void showFlights() {
        flightService.showAllFlights();
    }
}

