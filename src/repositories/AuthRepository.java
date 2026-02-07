package repositories;

import models.Role;

public class AuthRepository {
    public boolean canAddFlight(Role role) {
        return role == Role.ADMIN;
    }
    public boolean canViewStats(Role role) {
        return role == Role.MANAGER;
    }
}
