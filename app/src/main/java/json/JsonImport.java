package json;

import android.util.Log;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import model.AllMatches;
import model.Match;

public class JsonImport {
    public static AllMatches importMatchesFPM (String competitionID){

        String url = "https://api.football-data.org/v2/competitions/" + competitionID + "/matches";

        Gson gson = new Gson();
        AllMatches matchesAllSeason = null;
        try {
            matchesAllSeason = gson.fromJson(JsonService.getJsonStringByUrl(url), AllMatches.class);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  matchesAllSeason;
    }

}
