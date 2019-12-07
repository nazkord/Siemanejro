package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetPostponedDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setMatchStatusText(betViewHolder.getMatchStatus(), "POSTPONED");
    }
}
