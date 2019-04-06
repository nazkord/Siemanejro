package json;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JsonService { //Some net stuff.
    public static String JsonString (String websiteAddress) { //Static allows us to use without creating an object.
        try {

            //URL url = new URL("https://api.football-data.org/v2/matches");
            URL url = new URL(websiteAddress);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("GET"); //Expection
            http.setRequestProperty("X-Auth-Token", "21beafd163e54803a4e4ecbb25385336");
            http.setUseCaches(false);
            http.setAllowUserInteraction(false);
            http.connect(); //Exception

            if (http.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
                String line;
                StringBuilder final_string = new StringBuilder();

                line = bufferedReader.readLine();

                while (line != null) {
                    final_string.append(line + "\n");
                    line = bufferedReader.readLine();
                }

                bufferedReader.close(); //Excpetion
                return final_string.toString();

            } else {
                return "Error One";
            }

        }

        catch (ProtocolException e) {
            return "Error Two";
        }

        catch (IOException e){
            return "Error Three";
        }
    }

}

