package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.support.v7.widget.RecyclerView;

import model.Bet;
import model.Match;

public class BetPostponedDrawer extends BetDrawer {

    @Override
    public void drawBet(Bet bet) {
        setCurrentMatch(bet.getMatch());
        setMatchStatusText("POSTPONED");
    }
}
