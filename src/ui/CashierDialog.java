package ui;

public class CashierDialog {

    public void hello() {
        System.out.println("\nCashier: Hello! Welcome to our ticket office.");
        System.out.println("Cashier: You can enter 0 at any time to cancel.");
    }

    public void askFlight() {
        System.out.println("Cashier: Please choose a flight.");
    }

    public void confirm() {
        System.out.println("Cashier: Booking your ticket now...");
    }

    public void done(int bookingId) {
        System.out.println("Cashier: Your booking ID is " + bookingId + ".");
        System.out.println("Cashier: Have a nice flight!");
    }

    public void cancelledByUser() {
        System.out.println("Cashier: No problem. Returning to menu.");
    }
}
