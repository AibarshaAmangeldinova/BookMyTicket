package security;

import models.Role;

public class AuthService {
    public void loginAs(Role role) {
        CurrentUser.setRole(role);
    }
}

