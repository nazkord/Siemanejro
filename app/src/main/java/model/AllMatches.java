package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import json.UtcConverter;
import model.Match;

public class AllMatches {
    private ArrayList<Match> matches;

    public  ArrayList<Match> getMatches() {
        return matches;
    }

    // map contains matches id and scores
    private Map<String, Score> usersMatches = new HashMap<>();

    public Map<String, Score> getUsersMatches() {
        return usersMatches;
    }

    public void update (){
        for (Match match : matches){
            match.setMatchDate(UtcConverter.utcConverter(match.getUtcDate()));
        }
    }

    public void updateUserMatches() {

    }



}
