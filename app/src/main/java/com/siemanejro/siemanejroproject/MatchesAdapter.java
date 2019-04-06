package com.siemanejro.siemanejroproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import model.Match;
import model.Score;

public class MatchesAdapter extends ArrayAdapter<Match> {

    private Context context;
    private ArrayList<Match> matches;

    public MatchesAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
        this.matches = matches;
        this.context = context;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.match_item, parent, false);
        }

        Match currentMatch = matches.get(position);

        TextView date = (TextView) convertView.findViewById(R.id.dateTime);
        date.setText(currentMatch.getUtcDate());

        TextView teamName1 = (TextView) convertView.findViewById(R.id.teamName1);
        teamName1.setText(currentMatch.getHomeTeam().getName());

        TextView teamName2 = (TextView) convertView.findViewById(R.id.teamName2);
        teamName2.setText(currentMatch.getAwayTeam().getName());

        return convertView;
    }
}