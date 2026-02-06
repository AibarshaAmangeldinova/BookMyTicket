package security;

import models.StaffAccount;
import repositories.AuthRepository;
import repositories.RepositoryFactory;

public class AuthService {

    private final AuthRepository repo;

    public AuthService() {
        this.repo = new RepositoryFactory().authRepo();
    }

    public boolean login(String username, String password) {
        StaffAccount acc = repo.findByUsernameAndPassword(username, password);
        if (acc == null) return false;

        CurrentUser.set(acc.username, acc.role);
        return true;
    }
}
