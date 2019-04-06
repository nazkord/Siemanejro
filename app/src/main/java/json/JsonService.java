package json;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JsonService<T> { //Some net stuff.
    static String TOKEN =  "21beafd163e54803a4e4ecbb25385336";
    public T getObjectFromJson(String urlTarget, Class<T> myClass) throws ExecutionException, InterruptedException {
        Gson gson = new Gson();
        String json;
        T result;

        json = getJsonStringByUrl(urlTarget);
        result = gson.fromJson(json, myClass);

        return result;
    }

    static  String getJsonStringByUrl(final String urlTarget) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL url = new URL(urlTarget);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                http.setRequestMethod("GET");
                http.setRequestProperty("X-Auth-Token", TOKEN);
                http.setUseCaches(false);
                http.setAllowUserInteraction(false);
                http.connect();

                int status = http.getResponseCode();
                if (status == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    br.close();
                    return sb.toString();

                }else {
                    throw new Exception("Status "+status);
                }
            }
        };
        Future<String> future = executor.submit(callable);
        executor.shutdown();
        return future.get();
    }


}

