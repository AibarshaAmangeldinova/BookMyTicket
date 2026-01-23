
package com.company.models;

public class Flight {

    private int id;
    private String origin;
    private String destination;
    private double price;
    private int availableSeats;

    public Flight(int id, String origin, String destination, double price, int availableSeats) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}
