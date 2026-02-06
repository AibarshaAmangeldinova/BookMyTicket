package utils;

import java.util.List;

public class TextTable {

    public static void print(String[] headers, List<String[]> rows) {
        if (headers == null || headers.length == 0) return;

        int cols = headers.length;
        int[] w = new int[cols];

        for (int i = 0; i < cols; i++) w[i] = headers[i].length();
        for (String[] r : rows) {
            for (int i = 0; i < cols && i < r.length; i++) {
                if (r[i] != null) w[i] = Math.max(w[i], r[i].length());
            }
        }

        System.out.println();
        printRow(headers, w);
        printLine(w);

        for (String[] r : rows) {
            String[] safe = new String[cols];
            for (int i = 0; i < cols; i++) safe[i] = (r != null && i < r.length && r[i] != null) ? r[i] : "";
            printRow(safe, w);
        }
    }

    private static void printLine(int[] w) {
        StringBuilder sb = new StringBuilder();
        for (int x : w) sb.append("-".repeat(Math.max(0, x + 3)));
        System.out.println(sb);
    }

    private static void printRow(String[] r, int[] w) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w.length; i++) {
            String cell = (i < r.length && r[i] != null) ? r[i] : "";
            sb.append(pad(cell, w[i])).append(" | ");
        }
        System.out.println(sb);
    }

    private static String pad(String s, int width) {
        if (s.length() >= width) return s;
        return s + " ".repeat(width - s.length());
    }
}
