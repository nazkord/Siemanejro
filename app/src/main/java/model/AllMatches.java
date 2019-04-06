package model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
    static public ArrayList<Match> getMatchesFromGivenDate(String date)
    {
        ArrayList<Match> matchList = new ArrayList<>();
        for(Match match : staticListOfMatches)
        {
           if(match.getUtcDate().substring(0,10).equals(date))
           {
               matchList.add(match);
           }
        }
        return matchList;
    }
}
