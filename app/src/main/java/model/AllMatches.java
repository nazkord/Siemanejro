package model;


import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import model.Match;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class AllMatches {
    private ArrayList<Match> matches = new ArrayList<>();

    public AllMatches() {
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public ArrayList<Match> getMatchesFromGivenDate(final String dateInString) {

        /// #TODO – make stream works

       /* return matches.stream()
                .map(Match.class::cast)
                .filter(Match -> Match.getUtcDate().substring(0,10).equals(dateInString))
                .collect(Collectors.toList()); */

       ArrayList<Match> appropriateMatches = new ArrayList<>();
       for(Match match : matches) {
           if(match.getUtcDate().substring(0,10).equals(dateInString)) {
               appropriateMatches.add(match);
           }
       }

       return appropriateMatches;
    }
}
