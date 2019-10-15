package com.siemanejro.siemanejroproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

import model.Match;

public class MatchesAdapter extends ArrayAdapter<Match> {

    private ArrayList<Match> matches;

    public MatchesAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
        this.matches = matches;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //TODO: work, but need to be done using recyclerView

        //if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.match_item, parent, false);
        //}

        Match currentMatch = matches.get(position);

        TextView date = (TextView) convertView.findViewById(R.id.dateTime);
        String text = currentMatch.getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        date.setText(finalText);


        TextView teamName1 = (TextView) convertView.findViewById(R.id.teamName1);
        teamName1.setText(currentMatch.getHomeTeam().getName());

        TextView teamName2 = (TextView) convertView.findViewById(R.id.teamName2);
        teamName2.setText(currentMatch.getAwayTeam().getName());

        EditText firstResult = (EditText) convertView.findViewById(R.id.result1);
        EditText secondResult = (EditText) convertView.findViewById(R.id.result2);

        //TODO: make status class ENUM (using different type of status [->website])

        if(currentMatch.getStatus().equals("FINISHED")) {

            firstResult.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
            firstResult.setFocusable(false);

            secondResult.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
            secondResult.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
            secondResult.setFocusable(false);

            switch (currentMatch.getScore().getWinner()) {
                case "HOME_TEAM": {
                    teamName1.setTypeface(null, Typeface.BOLD);
                    break;
                }
                case "AWAY_TEAM": {
                    teamName2.setTypeface(null, Typeface.BOLD);
                    break;
                }
                case "DRAW": {
                    //TODO: change sth appearance
                    break;
                }
            }
        }

        return convertView;
    }
}