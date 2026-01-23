package com.company.controllers;

import java.util.Scanner;


public class TestController {

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Say hello");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Hello!");
            } else if (choice == 0) {
                break;
            }
        }
    }
}
