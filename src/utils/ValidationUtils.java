package utils;

public class ValidationUtils {

    public static String normalizeDocType(String s) {
        if (s == null) return null;
        return s.trim().toUpperCase();
    }

    public static String normalizeTicketClass(String s) {
        if (s == null) return null;
        return s.trim().toUpperCase();
    }
}
