package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import json.UtcConverter;
import model.Match;

public class AllMatches {
    private ArrayList<Match> matches;
<<<<<<< HEAD
=======
    private static ArrayList<Match> staticListOfMatches = new ArrayList<>();
>>>>>>> 963efb273d05bff19be6c609466b159879c26c4b

    public  ArrayList<Match> getMatches() {
        return matches;
    }

<<<<<<< HEAD
    // map contains matches id and scores
    private Map<String, Score> usersMatches = new HashMap<>();

    public Map<String, Score> getUsersMatches() {
        return usersMatches;
=======
    public static void setStaticListOfMatches(ArrayList<Match> staticListOfMatches) {
        AllMatches.staticListOfMatches = staticListOfMatches;
    }

    public static ArrayList<Match> getStaticListOfMatches() {
        return staticListOfMatches;
>>>>>>> 963efb273d05bff19be6c609466b159879c26c4b
    }

    public void update (){
        for (Match match : matches){
            //match.setMatchDate(UtcConverter.utcConverter(match.getUtcDate()));
        }
    }
<<<<<<< HEAD

    public void updateUserMatches() {

    }



=======
>>>>>>> 963efb273d05bff19be6c609466b159879c26c4b
}
