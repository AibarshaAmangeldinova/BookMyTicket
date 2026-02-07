package repositories;

import models.Role;

public class AuthRepository {

    public boolean hasAccess(Role role, Role requiredRole) {
        return role == requiredRole;
    }

    public boolean canAddFlight(Role role) {
        return hasAccess(role, Role.ADMIN);
    }

    public boolean canViewStats(Role role) {
        return hasAccess(role, Role.MANAGER);
    }
}
