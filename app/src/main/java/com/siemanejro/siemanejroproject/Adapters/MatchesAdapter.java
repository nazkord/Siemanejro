package com.siemanejro.siemanejroproject.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

import model.Match;
import model.Status;

public class MatchesAdapter extends ArrayAdapter<Match> {

    private ArrayList<Match> matches;

    public MatchesAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
        this.matches = matches;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //TODO: work, but need to be done using recyclerView (using viewHolder)

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.match_item, parent, false);
        }

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
        firstResult.setText(null);

        EditText secondResult = (EditText) convertView.findViewById(R.id.result2);
        secondResult.setText(null);

        firstResult.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!firstResult.getText().toString().isEmpty()) {
                        int i = Integer.parseInt(firstResult.getText().toString());
                    }
                }
            }
        });

        secondResult.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    int i = Integer.parseInt(secondResult.getText().toString());
                }
            }
        });

        switch (Status.valueOf(currentMatch.getStatus())) {
            case IN_PLAY : {
                //TODO: in_live: minutes matches should display
                firstResult.setFocusable(false);
                firstResult.setText(currentMatch.getScore().getFullTime().getHomeTeam().toString());
                firstResult.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

                secondResult.setFocusable(false);
                secondResult.setText(currentMatch.getScore().getFullTime().getAwayTeam().toString());
                secondResult.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

                convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPink));
                break;
            }
            case PAUSED: {
                //TODO: display that 1 half time is end
                break;
            }
            case FINISHED: {
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
        }
        return convertView;
    }
}