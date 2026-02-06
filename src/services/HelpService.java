package services;

import utils.Console;

public class HelpService {

    public void cashierHello() {
        Console.println("\nCashier: Hello! Welcome to BookMyTicket.");
        Console.println("Cashier: How can I help you today?");
        Console.println("Cashier: If you want to stop the process at any step, just type 0.");
        Console.println("Cashier: Great, let’s start with the flight list!");
    }

    public void cashierAskChooseFlight() {
        Console.println("\nCashier: Please look at the flights on the screen.");
        Console.println("Cashier: Each flight has an ID, origin, destination, category and price.");
        Console.println("Cashier: Tell me the flight ID you want to book.");
    }

    public void cashierAskPassengerIntro() {
        Console.println("\nCashier: Nice choice!");
        Console.println("Cashier: Now I need passenger details for the ticket.");
        Console.println("Cashier: Please type your real name and your document information.");
        Console.println("Cashier: This is required for the booking confirmation.");
    }

    public void cashierAskSeatAndClass() {
        Console.println("\nCashier: Perfect!");
        Console.println("Cashier: Now we choose a seat and ticket class.");
        Console.println("Cashier: Important: the seat must be free for this flight.");
        Console.println("Cashier: Example seats: 8E, 12A, 3C.");
    }

    public void cashierProcessing() {
        Console.println("\nCashier: Thank you!");
        Console.println("Cashier: I am checking availability and creating the booking now...");
        Console.println("Cashier: Please wait a moment.");
    }

    public void cashierSeatTaken() {
        Console.println("\nCashier:  Sorry, this seat is already taken for this flight.");
        Console.println("Cashier: Please choose another seat number and try again.");
    }

    public void cashierSuccess(int bookingId) {
        Console.println("\nCashier:  Booking successful!");
        Console.println("Cashier: Your booking ID is: " + bookingId);
        Console.println("Cashier: Please save this booking ID. You can use it to cancel your booking.");
        Console.println("Cashier: Thank you for choosing BookMyTicket. Have a great trip!");
    }

    public void cashierCancelled() {
        Console.println("\nCashier: No problem! We stopped the booking process.");
        Console.println("Cashier: Returning to the main menu.");
    }

    public void cashierValidationError(String err) {
        Console.println("\nCashier:  Sorry, some information is not valid.");
        Console.println("Cashier: " + err);
        Console.println("Cashier: Please start again and enter correct data.");
    }

    public void cashierSayFlightNotFound() {
        Console.println("\nCashier:  I cannot find this flight ID.");
        Console.println("Cashier: Please check the list and choose a correct flight number.");
    }

    public void cashierCancelIntro() {
        Console.println("\nCashier: You chose 'Cancel booking'.");
        Console.println("Cashier: Please enter your booking ID.");
        Console.println("Cashier: If it exists, I will cancel it and calculate your refund.");
    }

    public void cashierCancelNotFound() {
        Console.println("\nCashier:  Booking not found (or already canceled).");
        Console.println("Cashier: Please check the booking ID and try again.");
    }

    public void cashierCancelSuccess(int refund) {
        Console.println("\nCashier:  Booking canceled successfully.");
        Console.println("Cashier: Your refund amount is: " + refund + " KZT.");
        Console.println("Cashier: Thank you! See you again.");
    }
}

