package utils;

import java.util.Scanner;

public class InputUtils {

    public static Integer readInt(Scanner sc, String prompt, boolean allowCancel) {
        while (true) {
            System.out.print(prompt + (allowCancel ? " (0 = cancel): " : ": "));
            String s = sc.nextLine().trim();

            if (allowCancel && s.equals("0")) return null;

            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Enter a number.");
            }
        }
    }

    public static String readString(Scanner sc, String prompt, boolean allowCancel) {
        while (true) {
            System.out.print(prompt + (allowCancel ? " (0 = cancel): " : ": "));
            String s = sc.nextLine().trim();

            if (allowCancel && s.equals("0")) return null;
            if (!s.isEmpty()) return s;

            System.out.println("Cannot be empty.");
        }
    }
}
