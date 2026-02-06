package controllers;

import utils.Console;

public class CashierDialog {
    public void hello() {
        Console.println("\nCashier: Hello! Welcome to BookMyTicket.");
        Console.println("Cashier: I’m your cashier today. I will help you book a ticket step by step.");
        Console.println("Cashier: Please check the flight list. Choose the flight id you want.");
        Console.println("Cashier: If you want to stop at any step, type 0 and we will return to the main menu.");
    }

    public void passengerIntro() {
        Console.println("\nCashier: Great choice!");
        Console.println("Cashier: Now I need passenger details for your ticket.");
        Console.println("Cashier: Please tell me your first name, last name, phone number, and document info.");
        Console.println("Cashier: This is needed to confirm the booking in the database.");
    }

    public void seatIntro() {
        Console.println("\nCashier: Perfect. Now we will pick your seat and ticket class.");
        Console.println("Cashier: Important: seat number must be free for this flight. I will check it automatically.");
        Console.println("Cashier: Seat format example: 8E, 12A, 3C.");
    }

    public void processing() {
        Console.println("\nCashier: Thank you! I’m checking seat availability and saving your booking...");
        Console.println("Cashier: Please wait a moment.");
    }

    public void success(int id) {
        Console.println("\nCashier:  Booking successful!");
        Console.println("Cashier: Your booking ID is: " + id);
        Console.println("Cashier: Please keep this ID. You can cancel the booking and request a refund later.");
        Console.println("Cashier: Have a nice trip!");
    }

    public void fail() {
        Console.println("\nCashier:  Booking failed.");
        Console.println("Cashier: Possible reasons: wrong flight id, seat already taken, or database error.");
        Console.println("Cashier: Please try again from the menu.");
    }

    public void cancelled() {
        Console.println("\nCashier: Okay, cancelled. Returning to the main menu.");
    }
}
