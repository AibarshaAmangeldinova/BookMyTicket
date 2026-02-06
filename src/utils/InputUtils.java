package utils;

import java.util.Scanner;

public class InputUtils {

    public static Integer readIntOrCancel(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + " (0 = cancel): ");
            String s = sc.nextLine().trim();

            if (s.equals("0")) return null;

            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Enter a number.");
            }
        }
    }

    public static String readStringOrCancel(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + " (0 = cancel): ");
            String s = sc.nextLine().trim();

            if (s.equals("0")) return null;
            if (!s.isEmpty()) return s;

            System.out.println("Cannot be empty.");
        }
    }
}
