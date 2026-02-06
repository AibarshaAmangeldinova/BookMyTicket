package security;

import repositories.RepositoryFactory;
import repositories.StaffRepository;

public class AuthService {
    private final StaffRepository staffRepo = new RepositoryFactory().staffRepo();

    public boolean login(String username, String password) {
        Role role = staffRepo.findRole(username, password);
        if (role == null) return false;
        CurrentUser.set(username, role);
        return true;
    }
}
