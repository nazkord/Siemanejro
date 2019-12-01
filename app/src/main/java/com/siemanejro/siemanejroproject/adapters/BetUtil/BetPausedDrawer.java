package com.siemanejro.siemanejroproject.adapters.BetUtil;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import model.Match;

public class BetPausedDrawer extends BetDrawer {

    public BetPausedDrawer(RecyclerView.ViewHolder holder, Match match) {
        super(holder, match);
    }

    @Override
    public void drawBet() {
        setMatchStatusText("HT");
        setMatchStatusViewColor(Color.RED);

        setHomeTeamResult();
        setHomeTeamViewColor(Color.RED);

        setAwayTeamResult();
        setAwatTeamViewColor(Color.RED);
    }
}
