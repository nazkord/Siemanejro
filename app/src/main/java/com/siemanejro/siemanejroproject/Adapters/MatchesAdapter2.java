package com.siemanejro.siemanejroproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.util.List;

import model.Bet;
import model.Match;

public class MatchesAdapter2 extends RecyclerView.Adapter<MatchesAdapter2.ViewHolder> {

    private List<Bet> bets;

    public MatchesAdapter2(List<Bet> bets) {
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

        //set date of match
        String text = currentBet.getMatch().getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        viewHolder.date.setText(finalText);

        //set name of teams
        viewHolder.team1.setText(currentBet.getMatch().getHomeTeam().getName());
        viewHolder.team2.setText(currentBet.getMatch().getAwayTeam().getName());

        viewHolder.result1.setText(null);
        viewHolder.result2.setText(null);

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
