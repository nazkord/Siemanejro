package communication;

import java.util.List;
import java.util.Optional;

import model.Bet;
import model.Match;
import model.User;

public interface SiemajeroCommunication {
    Optional<User> loginUser(String userName, String password);
    List<Match> getMatchesByCompetition(Long competitionId);
    User createUser(User newUser);
    List<Bet> getUsersBet(Long userId);
    User getLoggedInUser();
    void setLoggedInUser(User uSer);
}
