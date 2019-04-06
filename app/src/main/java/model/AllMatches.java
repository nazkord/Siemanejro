package model;

import java.util.ArrayList;

import json.UtcConverter;
import model.Match;

public class AllMatches {
    private ArrayList<Match> matches;
    private static ArrayList<Match> staticListOfMatches = new ArrayList<>();

    public  ArrayList<Match> getMatches() {
        return matches;
    }

    public static void setStaticListOfMatches(ArrayList<Match> staticListOfMatches) {
        AllMatches.staticListOfMatches = staticListOfMatches;
    }

    public static ArrayList<Match> getStaticListOfMatches() {
        return staticListOfMatches;
    }

    public void update (){
        for (Match match : matches){
            //match.setMatchDate(UtcConverter.utcConverter(match.getUtcDate()));
        }
    }
}
