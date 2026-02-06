package ui;

public class CashierDialog {

    public void greet() {
        System.out.println("\nCashier: Hello! Welcome to BookMyTicket.");
        System.out.println("Cashier: I will help you book a flight ticket today.");
        System.out.println("Cashier: You can type 0 at any moment to cancel and return to the menu.");
        System.out.println("Cashier: Let’s start!");
    }

    public void explainFlights() {
        System.out.println("\nCashier: Please look at the available flights.");
        System.out.println("Cashier: Choose the flight ID you want to book.");
    }

    public void askPersonalData() {
        System.out.println("\nCashier: Great! Now I need your passenger information.");
        System.out.println("Cashier: Please answer the questions carefully.");
    }

    public void askSeatAndClass() {
        System.out.println("\nCashier: Now choose your seat and ticket class.");
        System.out.println("Cashier: Seat number must be unique for the selected flight.");
    }

    public void processing() {
        System.out.println("\nCashier: Thank you! Booking your ticket now...");
    }

    public void success(int bookingId) {
        System.out.println("\nCashier: ✅ Booking successful!");
        System.out.println("Cashier: Your booking ID is: " + bookingId);
        System.out.println("Cashier: Have a nice trip!");
    }

    public void seatTaken() {
        System.out.println("\nCashier: ❌ Sorry, this seat is already taken for this flight.");
        System.out.println("Cashier: Please choose another seat and try again.");
    }

    public void cancelledByUser() {
        System.out.println("\nCashier: No problem! Returning to the menu...");
    }

    public void cancelBookingIntro() {
        System.out.println("\nCashier: You chose to cancel a booking.");
        System.out.println("Cashier: Enter your booking ID and I will cancel it.");
    }

    public void cancelSuccess(int refund) {
        System.out.println("\nCashier: ✅ Booking cancelled.");
        System.out.println("Cashier: Refund: " + refund + " KZT.");
    }

    public void cancelNotFound() {
        System.out.println("\nCashier: ❌ Booking ID not found.");
    }
}
