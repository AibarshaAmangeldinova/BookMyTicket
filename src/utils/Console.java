package utils;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void println(String text) {
        System.out.println(text);
    }

    public static String readString(String label, boolean hide) {
        System.out.print(label + ": ");
        return scanner.nextLine();
    }

    public static int readInt(String label) {
        System.out.print(label + ": ");
        return Integer.parseInt(scanner.nextLine());
    }
}
