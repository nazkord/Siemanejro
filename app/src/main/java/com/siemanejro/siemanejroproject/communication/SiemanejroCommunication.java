package com.siemanejro.siemanejroproject.communication;

import java.util.List;
import java.util.Optional;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.BetList;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.User;
import okhttp3.HttpUrl;
import okhttp3.Request;

public interface SiemanejroCommunication {
    Optional<User> loginUser(String userName, String password);
    List<Match> getMatchesByCompetitions(List<Long> competitionIds);
    //TODO: those method should retrieve response from API
    Request makeUserGetRequest(HttpUrl.Builder builder);
    boolean postUsersBet(BetList betList);
    List<Bet> getLoggedInUserBets();
    void setLoggedInUser(User uSer);
}
