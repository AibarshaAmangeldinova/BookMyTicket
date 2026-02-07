package utils;

import models.DocumentType;
import models.TicketClass;

public class Validator {

    public static boolean validName(String s) {
        return s != null && s.trim().length() >= 2;
    }

    public static boolean validPhone(String s) {
        if (s == null) return false;
        String t = s.trim();
        return t.matches("[+0-9]{6,20}");
    }

    public static boolean validSeat(String s) {
        if (s == null) return false;
        String t = s.trim().toUpperCase();
        return t.matches("[0-9]{1,2}[A-F]"); // пример: 8E, 12B
    }

    public static boolean validTicketClass(String s) {
        if (s == null) return false;
        String t = s.trim().toUpperCase();
        return t.equals(TicketClass.ECONOMY.name()) || t.equals(TicketClass.BUSINESS.name());
    }

    public static boolean validDocType(String s) {
        if (s == null) return false;
        String t = s.trim().toUpperCase();
        return t.equals(DocumentType.PASSPORT.name()) || t.equals(DocumentType.ID_CARD.name());
    }

    public static boolean validDocNumber(String s) {
        if (s == null) return false;
        String t = s.trim();
        return t.length() >= 5 && t.length() <= 30;
    }
}
