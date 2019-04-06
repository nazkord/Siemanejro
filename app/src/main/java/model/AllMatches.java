package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

}
