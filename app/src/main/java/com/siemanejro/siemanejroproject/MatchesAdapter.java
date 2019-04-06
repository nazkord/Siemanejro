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
import java.util.List;

import model.Match;

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
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.one_item, parent, false);

            Match currentMatch = matches.get(position);

            TextView date = (TextView) listItem.findViewById(R.id.date);
            date.setText(currentMatch.getMatchDate());

            TextView teamName1 = (TextView) listItem.findViewById(R.id.teamName1);
            date.setText(currentMatch.getTeam1());

            TextView teamName2 = (TextView) listItem.findViewById(R.id.teamName2);
            date.setText(currentMatch.getTeam2());


        }
        return listItem;
    }
}

