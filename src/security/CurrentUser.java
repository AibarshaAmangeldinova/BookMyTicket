package security;

public class CurrentUser {
    private static String username;
    private static Role role;

    public static void set(String u, Role r) {
        username = u;
        role = r;
    }

    public static String getUsername() { return username; }
    public static Role getRole() { return role; }

    public static boolean hasAny(Role... roles) {
        if (role == null) return false;
        for (Role r : roles) if (role == r) return true;
        return false;
    }
}

