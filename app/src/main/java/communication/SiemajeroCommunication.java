package communication;

import java.util.List;
import java.util.Optional;

import model.Bet;
import model.BetList;
import model.Match;
import model.User;
import okhttp3.HttpUrl;
import okhttp3.Request;

public interface SiemajeroCommunication {
    Optional<User> loginUser(String userName, String password);
    List<Match> getMatchesByCompetition(Long competitionId);
    User createUser(User newUser);
    //TODO: those method should retrieve response from API
    Request makeUserGetRequest(HttpUrl.Builder builder);
    boolean postUsersBet(BetList betList);
    List<Bet> getLoggedInUserBets();
    void setLoggedInUser(User uSer);
}
