package com.siemanejro.siemanejroproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

import model.Bet;
import model.RVItems.RVBetItem;
import model.RVItems.RVBetPageItem;

public class RVMatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RVBetPageItem> betPageItems;

    public RVMatchesAdapter(ArrayList<RVBetPageItem> betPageItems) {
        this.betPageItems = betPageItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case RVBetPageItem.TYPE_BET : {   //bet layout
                view = inflater.inflate(R.layout.match_item, parent, false);
                return new BetViewHolder(view);
            }
            case RVBetPageItem.TYPE_LEAGUE: { //league name layout
                view = inflater.inflate(R.layout.league_item, parent, false);
                return new LeagueViewHolder(view);
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return betPageItems.get(position).getType();
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return betPageItems.size();
    }

    public RVBetPageItem getItem(int position) {
        return betPageItems.get(position);
    }

    class LeagueViewHolder extends RecyclerView.ViewHolder {
        ImageView leagueLogo;
        TextView leagueName;

        private LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            leagueLogo = itemView.findViewById(R.id.imageOfLeague);
            leagueName = itemView.findViewById(R.id.nameOfLeague);
        }
    }

    class BetViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView matchStatus;
        TextView team1;
        TextView team2;
        EditText result1;
        EditText result2;

        private BetViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateTime);
            matchStatus = itemView.findViewById(R.id.matchStatus);
            team1 = itemView.findViewById(R.id.teamName1);
            team2 = itemView.findViewById(R.id.teamName2);
            result1 = itemView.findViewById(R.id.result1);

            result1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // result will be null, when we recycler the view and set the result views to null in onBindViewHolder
                    String result = result1.getText().toString();
                    if (!result.isEmpty()) {
                        Integer homeTeamResultBet = Integer.valueOf(result);
                        RVBetItem betItem = (RVBetItem) betPageItems.get(getAdapterPosition());
                        betItem.getBet().getUserScore().getFullTime().setHomeTeam(homeTeamResultBet);
                    }
                }
            });

            result2 = itemView.findViewById(R.id.result2);

            result2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String result = result2.getText().toString();
                    if (!result.isEmpty()) {
                        Integer awayTeamResultBet = Integer.valueOf(result);
                        RVBetItem betItem = (RVBetItem) betPageItems.get(getAdapterPosition());
                        betItem.getBet().getUserScore().getFullTime().setAwayTeam(awayTeamResultBet);
                    }
                }
            });
        }
    }

    Long getMinuteOfMatch(String matchTimeString) {
        LocalTime matchTime = LocalTime.parse(matchTimeString.substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }

}
