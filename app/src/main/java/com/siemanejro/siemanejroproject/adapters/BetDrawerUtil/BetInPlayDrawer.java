package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;

import model.Bet;
import model.Match;

public class BetInPlayDrawer extends BetDrawer {

    @Override
    public void drawBet(Bet bet) {
        setCurrentMatch(bet.getMatch());
        setHomeTeamResult();
        setHomeTeamViewColor();

        setAwayTeamResult();
        setAwayTeamViewColor();

        setMatchStatusText(getMinuteOfMatch() + "'");
        setMatchStatusViewColor();

        switch (bet.getMatch().getScore().getWinner()) {
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
