package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;

import model.Match;

public class BetInPlayDrawer extends BetDrawer {

    public BetInPlayDrawer(RecyclerView.ViewHolder holder, Match match) {
        super(holder, match);
    }

    @Override
    public void drawBet() {
        setHomeTeamResult();
        setHomeTeamViewColor();

        setAwayTeamResult();
        setAwayTeamViewColor();

        setMatchStatusText(getMinuteOfMatch() + "'");
        setMatchStatusViewColor(Color.RED);

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

        setItemBackgroundColorPink();
    }

}
