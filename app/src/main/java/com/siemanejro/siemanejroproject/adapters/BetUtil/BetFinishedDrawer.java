package com.siemanejro.siemanejroproject.adapters.BetUtil;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;

import model.Match;

public class BetFinishedDrawer extends BetDrawer {

    public BetFinishedDrawer(RecyclerView.ViewHolder holder, Match match) {
        super(holder, match);
    }

    @Override
    public void drawBet() {
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
