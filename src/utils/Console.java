package utils;

import java.util.Scanner;

public class Console {
    private static final Scanner sc = new Scanner(System.in);

    public static void println(String s) { System.out.println(s); }

    public static String readString(String prompt, boolean allowCancel) {
        while (true) {
            System.out.print(prompt + (allowCancel ? " (0 = cancel): " : ": "));
            String s = sc.nextLine().trim();
            if (allowCancel && s.equals("0")) return null;
            if (!s.isEmpty()) return s;
            System.out.println("Cannot be empty.");
        }
    }

    public static Integer readInt(String prompt, boolean allowCancel) {
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
}
