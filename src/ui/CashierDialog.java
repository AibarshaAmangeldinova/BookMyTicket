package ui;

public class CashierDialog {

    public void hello() {
        System.out.println("\nCashier: Hello! Welcome to our ticket office.");
        System.out.println("Cashier: Type 0 anytime to cancel.");
    }

    public void askFlight() {
        System.out.println("Cashier: Choose a flight.");
    }

    public void confirm() {
        System.out.println("Cashier: Processing your booking...");
    }

    public void done(int bookingId) {
        System.out.println("Cashier: Your booking ID is " + bookingId);
        System.out.println("Cashier: Have a nice flight!");
    }

    public void cancelledByUser() {
        System.out.println("Cashier: Returning to menu.");
    }
}
