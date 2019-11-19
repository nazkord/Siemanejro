package com.siemanejro.siemanejroproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

import model.Bet;

public class RVBetsAdapter extends RecyclerView.Adapter<RVBetsAdapter.ViewHolder> {

    private ArrayList<Bet> bets;

    public RVBetsAdapter(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bet_item, viewGroup, false);

        return new ViewHolder(view); // Return a new holder instance
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Bet currentBet = bets.get(position);

        TextView date = viewHolder.date;
        TextView teamName1 = viewHolder.team1;
        TextView teamName2 = viewHolder.team2;
        TextView resultOfTeam1 = viewHolder.matchResult1;
        TextView colon = viewHolder.colon;
        TextView resultOfTeam2 = viewHolder.matchResult2;
        TextView userBet1 = viewHolder.betResult1;
        TextView userBet2 = viewHolder.betResult2;
        TextView betResult = viewHolder.resultOfBet;

        //set default view
        betResult.setText(null);
        colon.setVisibility(View.VISIBLE);

        String text = currentBet.getMatch().getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        date.setText(finalText);

        teamName1.setText(currentBet.getMatch().getHomeTeam().getName());
        teamName2.setText(currentBet.getMatch().getAwayTeam().getName());

        Integer resultHomeTeam = currentBet.getMatch().getScore().getFullTime().getHomeTeam();
        if(resultHomeTeam != null) {
            resultOfTeam1.setText(String.valueOf(resultHomeTeam));
            betResult.setText(String.valueOf(currentBet.getResult()));
        } else {
            colon.setVisibility(View.INVISIBLE);
        }

        Integer resultAwayTeam = currentBet.getMatch().getScore().getFullTime().getAwayTeam();
        if(resultAwayTeam != null) {
            resultOfTeam2.setText(String.valueOf(resultAwayTeam));
        }

        userBet1.setText(String.valueOf(currentBet.getUserScore().getFullTime().getHomeTeam()));

        userBet2.setText(String.valueOf(currentBet.getUserScore().getFullTime().getAwayTeam()));
    }

    @Override
    public int getItemCount() {
        return bets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView team1;
        TextView team2;
        TextView matchResult1;
        TextView colon;
        TextView matchResult2;
        TextView betResult1;
        TextView betResult2;
        TextView resultOfBet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dataOfMatch);
            team1 = itemView.findViewById(R.id.teamName1);
            team2 = itemView.findViewById(R.id.teamName2);
            matchResult1 = itemView.findViewById(R.id.matchResult1);
            matchResult2 = itemView.findViewById(R.id.matchResult2);
            colon = itemView.findViewById(R.id.colon1);
            betResult1 = itemView.findViewById(R.id.userBet1);
            betResult2 = itemView.findViewById(R.id.userBet2);
            resultOfBet = itemView.findViewById(R.id.betResult);
        }
    }
}
