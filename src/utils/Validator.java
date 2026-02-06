package utils;

import java.util.Set;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern PHONE = Pattern.compile("^\\+?\\d{7,15}$");
    private static final Pattern SEAT = Pattern.compile("^\\d{1,2}[A-F]$");

    private static final Set<String> DOC_TYPES = Set.of("PASSPORT", "ID_CARD");
    private static final Set<String> CLASSES = Set.of("ECONOMY", "BUSINESS");

    public static String validatePassenger(String first, String last, String phone,
                                           String docType, String docNumber,
                                           String seat, String ticketClass) {
        if (first == null || first.trim().length() < 2) return "First name is too short.";
        if (last == null || last.trim().length() < 2) return "Last name is too short.";

        if (!PHONE.matcher(phone).matches()) return "Phone must be like +77001234567 (7-15 digits).";
        String dt = docType.trim().toUpperCase();
        if (!DOC_TYPES.contains(dt)) return "Document type must be PASSPORT or ID_CARD.";

        if (docNumber.trim().length() < 4) return "Document number is too short.";

        String seatUp = seat.trim().toUpperCase();
        if (!SEAT.matcher(seatUp).matches()) return "Seat must look like 8E or 12A (A-F).";

        String cl = ticketClass.trim().toUpperCase();
        if (!CLASSES.contains(cl)) return "Ticket class must be ECONOMY or BUSINESS.";

        return null;
    }
}
