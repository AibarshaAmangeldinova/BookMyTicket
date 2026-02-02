package models;

public class Flight {

    public int id;
    public String fromCity;
    public String toCity;
    public int price;

    // Default constructor
    public Flight() {
    }

    // Main constructor
    public Flight(int id, String fromCity, String toCity, int price) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public int getPrice() {
        return price;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Display flight info
    public void printInfo() {
        System.out.println("Flight ID: " + id);
        System.out.println("From: " + fromCity);
        System.out.println("To: " + toCity);
        System.out.println("Price: " + price);
    }
}
