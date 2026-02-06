package security;

public class CurrentUser {
    private static String username;
    private static Role role;

    public static void set(String u, Role r) {
        username = u;
        role = r;
    }

    public static String username() { return username; }
    public static Role role() { return role; }

    public static boolean hasRole(Role r) {
        return role == r;
    }
}
