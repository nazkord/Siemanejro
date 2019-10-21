package communication;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import loginUtils.SharedPrefUtil;
import model.Bet;
import model.Match;
import model.User;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class SiemajeroOkHttpCommunication implements SiemajeroCommunication {

    private OkHttpClient client;
    private User loggedInUser;
    private ObjectMapper objectMapper;
    private static String basicUrl = "http://192.168.0.104:8080";

    SiemajeroOkHttpCommunication() {
        init();
    }

    private void init() {
        client = new OkHttpClient.Builder()
                //TODO: check it out
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .callTimeout(60, TimeUnit.SECONDS).build();

        objectMapper = new ObjectMapper();

    }

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public List<Match> getMatchesByCompetition(Long competitionId) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse
                (basicUrl + "/matches"))
                .newBuilder()
                .addQueryParameter("competitionId", competitionId.toString());

        Request userRequest = new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Authorization", Credentials.basic(loggedInUser.getName(), loggedInUser.getPassword()))
                .build();

        //TODO: this code is repeating, maybe consider replace it with some methods

        try {
            ResponseBody responseBody = client.newCall(userRequest).execute().body();
            if (responseBody != null) {
                return objectMapper.readValue(responseBody.string(), new TypeReference<List<Match>>() {
                });
            } else {
                //TODO: do sth with null responseBody
            }
        } catch (Exception e) {
            //TODO: add to log
            return null;
        }
        return null;
    }

    @Override
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public List<Bet> getUsersBet(Long userId) {

        //TODO: save bets from server for better user experience (latest bets can show even without network)

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(basicUrl + "/bets"))
                .newBuilder();

        Request userRequest = new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Authorization", Credentials.basic(loggedInUser.getName(), loggedInUser.getPassword()))
                .build();


        try {
            ResponseBody responseBody = client.newCall(userRequest).execute().body();
            if (responseBody != null) {
                return objectMapper.readValue(responseBody.string(), new TypeReference<List<Bet>>() {
                });
            } else {
                //TODO: do sth with null responseBody
            }
        } catch (Exception e) {
            //TODO: add to log
            return null;
        }
        return null;
    }

    @Override
    public Optional<User> loginUser(String userName, String password) {

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(basicUrl + "/users"))
                .newBuilder()
                .addQueryParameter("userName", userName);

        Request userRequest = new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Authorization", Credentials.basic(userName, password))
                .build();

        try {
            ResponseBody responseBody = client.newCall(userRequest).execute().body();
            //TODO: do sth with null responseBody (string???)
            List<User> users = objectMapper.readValue(responseBody.string(), new TypeReference<List<User>>() {});

            Optional.ofNullable(users).ifPresent(u -> {
                loggedInUser = u.get(0);
            });

        } catch (Exception e) {
            //TODO: add to log
            return Optional.empty();
        }

        return Optional.ofNullable(loggedInUser);
    }

    @Override
    public User createUser(User newUser) {
        return null;
    }
}
