package communication;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import model.Bet;
import model.BetList;
import model.Match;
import model.User;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class SiemajeroOkHttpCommunication implements SiemajeroCommunication {

    private OkHttpClient client;
    private User loggedInUser;
    private ObjectMapper objectMapper;
    private static String basicUrl = "http://192.168.1.119:8080";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    SiemajeroOkHttpCommunication() {
        init();
    }

    private void init() {
        client = new OkHttpClient.Builder()
                //TODO: check it out (this was made only for tests)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .callTimeout(60, TimeUnit.SECONDS).build();

        objectMapper = new ObjectMapper();
    }

    @Override
    public Request makeUserGetRequest(HttpUrl.Builder urlBuilder) {
        return new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Authorization", Credentials.basic(loggedInUser.getName(), loggedInUser.getPassword()))
                .build();
    }

    @Override
    public boolean postUsersBet(BetList betList) {
        String betListInJson = "";

        for(Bet bet: betList) {
            bet.setUser(loggedInUser);
        }

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse
                (basicUrl + "/bets"))
                .newBuilder();

        try {
            betListInJson = objectMapper.writeValueAsString(betList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(betListInJson, JSON);
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Authorization", Credentials.basic(loggedInUser.getName(), loggedInUser.getPassword()))
                .post(requestBody)
                .build();

        try {
            //TODO: should I retrieve responseBody and get smth useful from there?
            client.newCall(request).execute().body();
        } catch (IOException e) {
            Log.e("Error while posting bets to Rest", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Match> getMatchesByCompetitions(List<Long> competitionIds) {
        StringBuilder idsBuilder = new StringBuilder();
        competitionIds.forEach(id -> {
            idsBuilder.append(id.toString());
            idsBuilder.append(",");
        });
        idsBuilder.deleteCharAt(idsBuilder.length()-1);

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse
                (basicUrl + "/matches"))
                .newBuilder()
                .addQueryParameter("competitionIds", idsBuilder.toString());

        Request userRequest = makeUserGetRequest(urlBuilder);

        //TODO: this code is repeating, maybe consider replace it with some methods

        try {
            ResponseBody responseBody = client.newCall(userRequest).execute().body();
            if (responseBody != null) {
                return objectMapper.readValue(responseBody.string(), new TypeReference<List<Match>>() {
                });
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            Log.e("Error getting matches from server",e.getMessage());
            //TODO: should return something that can be treated as error in activity
            //TODO: create new (my own) runtime ex and throw it here
            return null;
        }
    }

    @Override
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public List<Bet> getLoggedInUserBets() {

        //TODO: save bets from server for better user experience (latest bets can show even without network)

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(basicUrl + "/bets"))
                .newBuilder();

        Request userRequest = makeUserGetRequest(urlBuilder);

        try {
            ResponseBody responseBody = client.newCall(userRequest).execute().body();
            if (responseBody != null) {
                return objectMapper.readValue(responseBody.string(), new TypeReference<List<Bet>>() {
                });
            } else {
                return Collections.emptyList();
            }
        } catch (IOException e) {
            //TODO: which way is better?
            Log.e("Error while getting bets", e.getMessage());
//            throw new IOException("Error while getting bets");
            return Collections.emptyList();
        }
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
            Log.e("Error while getting user for logging", e.getMessage());
            return Optional.empty();
        }
        return Optional.ofNullable(loggedInUser);
    }

    @Override
    public User createUser(User newUser) {
        return null;
    }
}