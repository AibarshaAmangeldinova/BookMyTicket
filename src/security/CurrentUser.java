package security;

import models.Role;

public class CurrentUser {
    private static Role role = Role.CASHIER;

    public static void setRole(Role r) { role = r; }
    public static Role getRole() { return role; }
    public static boolean hasRole(Role r) { return role == r; }
}

