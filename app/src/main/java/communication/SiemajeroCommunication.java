package communication;

import java.util.Optional;

import model.User;

public interface SiemajeroCommunication {
    Optional<User> loginUser(String userName, String password);
    User createUser(User newUser);
}
