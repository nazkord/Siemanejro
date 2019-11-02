package com.siemanejro.siemanejroproject.Adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Bet;
import model.Match;
import model.Status;

public class RVMatchesAdapter extends RecyclerView.Adapter<RVMatchesAdapter.ViewHolder> {

    private ArrayList<Bet> bets;

    public RVMatchesAdapter(ArrayList<Bet> bets) {
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
        TextView matchStatus = viewHolder.matchStatus;
        EditText result1 = viewHolder.result1;
        EditText result2 = viewHolder.result2;
        TextView team1 = viewHolder.team1;
        TextView team2 = viewHolder.team2;

        //set status of match
        matchStatus.setText(null);

        //set date of match
        //TODO: should be done this zonedDateTime
        String text = currentBet.getMatch().getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        viewHolder.date.setText(finalText);

        //set name of teams
        team1.setText(currentBet.getMatch().getHomeTeam().getName());
        team2.setText(currentBet.getMatch().getAwayTeam().getName());

        // set default results view
        result1.setText(null);
        result1.setTextColor(Color.BLACK);
        result1.setFocusableInTouchMode(true);
        result2.setText(null);
        result2.setTextColor(Color.BLACK);
        result2.setFocusableInTouchMode(true);

        ///setBackgroundColorToDefault
        viewHolder.itemView.setBackgroundColor(Color.WHITE);

        switch (Status.valueOf(currentMatch.getStatus())) {
            case POSTPONED: {
                matchStatus.setText("POSTPONED");
            }
            case IN_PLAY : {
                result1.setFocusable(false);
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
                result1.setTextColor(Color.RED);

                result2.setFocusable(false);
                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
                result2.setTextColor(Color.RED);

                String matchMinute = getMinuteOfMatch(currentMatch.getUtcDate()) + "'";
                matchStatus.setText(matchMinute);
                matchStatus.setTextColor(Color.RED);

                switch (currentMatch.getScore().getWinner()) {
                    case "HOME_TEAM" : {
                        team1.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                    case "AWAY_TEAM" : {
                        team2.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                }

                viewHolder.itemView.setBackgroundColor(Color.rgb(255,230,238));
                break;
            }
            case PAUSED: {
                matchStatus.setText("HF");
                matchStatus.setTextColor(Color.RED);

                result1.setFocusable(false);
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
                result1.setTextColor(Color.RED);

                result2.setFocusable(false);
                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
                result2.setTextColor(Color.RED);

                break;
            }
            case FINISHED: {
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
                result1.setFocusable(false);

                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
                result2.setFocusable(false);

                matchStatus.setText("FT");

                switch (currentMatch.getScore().getWinner()) {
                    case "HOME_TEAM" : {
                        team1.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                    case "AWAY_TEAM" : {
                        team2.setTypeface(null, Typeface.BOLD);
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

    public Bet getItem(int position) {
        return bets.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView matchStatus;
        TextView team1;
        TextView team2;
        EditText result1;
        EditText result2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateTime);
            matchStatus = itemView.findViewById(R.id.matchStatus);
            team1 = itemView.findViewById(R.id.teamName1);
            team2 = itemView.findViewById(R.id.teamName2);
            result1 = itemView.findViewById(R.id.result1);
            result2 = itemView.findViewById(R.id.result2);
        }
    }

    Long getMinuteOfMatch(String matchTimeString) {
        LocalTime matchTime = LocalTime.parse(matchTimeString.substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }

}
