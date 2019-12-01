package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.support.v7.widget.RecyclerView;

import model.Match;

public class BetPostponedDrawer extends BetDrawer {
    public BetPostponedDrawer(RecyclerView.ViewHolder holder, Match match) {
        super(holder, match);
    }

    @Override
    public void drawBet() {
        setMatchStatusText("POSTPONED");
    }
}
