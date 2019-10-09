package communication;

import java.util.List;
import java.util.Optional;

import model.Bet;
import model.Match;
import model.User;

public interface SiemajeroCommunication {
    Optional<User> loginUser(String userName, String password);
    User createUser(User newUser);
    List<Bet> getUsersBet(Long userId);
}
