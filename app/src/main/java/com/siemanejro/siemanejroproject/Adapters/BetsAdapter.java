package com.siemanejro.siemanejroproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

import model.Bet;

public class BetsAdapter extends ArrayAdapter<Bet> {

    private ArrayList<Bet> bets;

    public BetsAdapter(Context context, ArrayList<Bet> bets) {
        super(context, 0, bets);
        this.bets = bets;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //TODO: work, but need to be done using recyclerView (check)

        if (convertView == null) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.bet_item, parent, false);
        }

        Bet currentBet = bets.get(position);

        TextView date = convertView.findViewById(R.id.dataOfMatch);
        String text = currentBet.getMatch().getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        date.setText(finalText);


        TextView teamName1 = convertView.findViewById(R.id.teamName1);
        teamName1.setText(currentBet.getMatch().getHomeTeam().getName());

        TextView teamName2 = convertView.findViewById(R.id.teamName2);
        teamName2.setText(currentBet.getMatch().getAwayTeam().getName());

        TextView resultOfTeam1 = convertView.findViewById(R.id.matchResult1);
        //TODO: check whether there is a fullTimeResult
        resultOfTeam1.setText(currentBet.getMatch().getScore().getFullTime().getHomeTeam().toString());

        TextView resultOfTeam2 = convertView.findViewById(R.id.matchResult2);
        resultOfTeam2.setText(currentBet.getMatch().getScore().getFullTime().getAwayTeam().toString());

        TextView userBet1 = convertView.findViewById(R.id.userBet1);
        userBet1.setText(currentBet.getUserScore().getFullTime().getHomeTeam().toString());

        TextView userBet2 = convertView.findViewById(R.id.userBet2);
        userBet2.setText(currentBet.getUserScore().getFullTime().getAwayTeam().toString());

        return convertView;
    }
}
