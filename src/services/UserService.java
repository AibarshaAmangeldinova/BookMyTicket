package services;

import models.User;
import repositories.UserRepository;

public class UserService {
    private final UserRepository repo = new UserRepository();

    public int createUser(User u) {
        return repo.saveAndReturnId(u);
    }
}
