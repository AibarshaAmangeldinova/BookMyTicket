package utils;

import java.util.Scanner;

public class Console {
    private final Scanner sc = new Scanner(System.in);

    public void println(String s) { System.out.println(s); }
    public void print(String s) { System.out.print(s); }

    public String readLine(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    public Integer readInt(String prompt) {
        try {
            print(prompt);
            String line = sc.nextLine().trim();
            if (line.isEmpty()) return null;
            return Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("Enter a number.");
            return null;
        }
    }

    // спец-метод: 0 = cancel
    public String readLineCancelable(String prompt) {
        String s = readLine(prompt);
        if (s == null) return null;
        s = s.trim();
        if ("0".equals(s)) return null;
        return s;
    }

    public Integer readIntCancelable(String prompt) {
        Integer x = readInt(prompt);
        if (x == null) return null;
        if (x == 0) return null;
        return x;
    }
}
