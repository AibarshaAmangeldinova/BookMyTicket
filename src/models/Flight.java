package com.company.models;

public class Flight {
    public int id;
    public String fromCity;
    public String toCity;
    public int price;

    public Flight(int id, String fromCity, String toCity, int price) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.price = price;
    }
}
