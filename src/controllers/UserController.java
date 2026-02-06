package controllers;

import models.User;
import services.UserService;

public class UserController {
    private final UserService userService = new UserService();

    public int registerUser(String firstName, String lastName, String phone, String documentType, String documentNumber) {
        User u = new User();
        u.firstName = firstName;
        u.lastName = lastName;
        u.phone = phone;
        u.documentType = documentType;
        u.documentNumber = documentNumber;

        return userService.createUser(u);
    }
}

