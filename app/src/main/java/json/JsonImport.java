package json;

import com.google.gson.Gson;
import model.AllMatches;

public class JsonImport {
    public static AllMatches importMatchesFPM (String competitionID){

        String url = "https://api.football-data.org/v2/competitions/" + competitionID + "/matches";

        Gson gson = new Gson();
        AllMatches matchesAllSeason = gson.fromJson(JsonService.JsonString(url), AllMatches.class);
        return  matchesAllSeason;
    }

}
