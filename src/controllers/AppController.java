package controllers;

import security.AuthService;
import security.CurrentUser;
import utils.Console;

public class AppController {
    private final AuthService auth = new AuthService();
    private final MenuController menu = new MenuController();

    public void start() {
        Console.println("=== BookMyTicket (Console) ===");
        Console.println("Login examples: admin/admin123, manager/manager123, cashier/cashier123");

        if (!login()) {
            Console.println("Exit.");
            return;
        }

        Console.println("Logged in: " + CurrentUser.getUsername() + " (" + CurrentUser.getRole() + ")");
        menu.run();
    }

    private boolean login() {
        for (int i = 0; i < 3; i++) {
            String u = Console.readString("Username", false);
            String p = Console.readString("Password", false);
            if (auth.login(u, p)) return true;
            Console.println(" Wrong login. Try again.");
        }
        return false;
    }
}
