package com.siemanejro.siemanejroproject.Adapters;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLDisplay;

import model.Bet;
import model.Match;
import model.Status;

public class MatchesAdapter2 extends RecyclerView.Adapter<MatchesAdapter2.ViewHolder> {

    private ArrayList<Bet> bets;

    public MatchesAdapter2(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Inflate the custom layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_item, parent, false);

        return new ViewHolder(view); // Return a new holder instance
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Bet currentBet = bets.get(position);
        Match currentMatch = currentBet.getMatch();
        EditText result1 = viewHolder.result1;
        EditText result2 = viewHolder.result2;
        TextView team1 = viewHolder.team1;
        TextView team2 = viewHolder.team2;

        //set date of match
        String text = currentBet.getMatch().getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        viewHolder.date.setText(finalText);

        //set name of teams
        team1.setText(currentBet.getMatch().getHomeTeam().getName());
        team2.setText(currentBet.getMatch().getAwayTeam().getName());

        result1.setText(null);
        result2.setText(null);

        switch (Status.valueOf(currentMatch.getStatus())) {
            case IN_PLAY : {
                //TODO: in_live: minutes matches should display
                result1.setFocusable(false);
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
//                result1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

                result2.setFocusable(false);
                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
//                secondResult.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

//                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPink));
                break;
            }
            case PAUSED: {
                //TODO: display that 1 half time is end
                break;
            }
            case FINISHED: {
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
                result1.setFocusable(false);

                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
                result2.setFocusable(false);

                switch (currentMatch.getScore().getWinner()) {
                    case "HOME_TEAM": {
                        team1.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                    case "AWAY_TEAM": {
                        team2.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                    case "DRAW": {
                        //TODO: change sth appearance
                        break;
                    }
                }
            }
        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return bets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView team1;
        TextView team2;
        EditText result1;
        EditText result2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateTime);
            team1 = itemView.findViewById(R.id.teamName1);
            team2 = itemView.findViewById(R.id.teamName2);
            result1 = itemView.findViewById(R.id.result1);
            result2 = itemView.findViewById(R.id.result2);
        }
    }


}
