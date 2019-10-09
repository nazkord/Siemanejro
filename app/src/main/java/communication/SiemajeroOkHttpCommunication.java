package communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import model.Bet;
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
    private static String basicUrl = "http://192.168.0.102:8080";

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
    public List<Bet> getUsersBet(Long userId) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(basicUrl + "/bets"))
                .newBuilder();

//        Request userRequest = new Request.Builder()
//                .url(urlBuilder.build().toString())
//                .addHeader("Authorization", Credentials.basic(userName, password))
//                .build();
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
            //TODO: do sth with null responseBody
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
