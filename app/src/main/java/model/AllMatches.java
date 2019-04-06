package model;

import java.util.ArrayList;

import json.UtcConverter;
import model.Match;

public class AllMatches {
    ArrayList<Match> matches;

    public void update (){
        for (Match match : matches){
            match.setMatchDate(UtcConverter.utcConverter(match.getUtcDate()));
        }
    }
}
