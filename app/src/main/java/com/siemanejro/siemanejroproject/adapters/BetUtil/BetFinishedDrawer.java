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
        setHomeTeamResult();
        setAwayTeamResult();
        setMatchStatusText("FT");

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
