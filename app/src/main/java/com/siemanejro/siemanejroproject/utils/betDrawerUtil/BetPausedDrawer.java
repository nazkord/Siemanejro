package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetPausedDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setMatchStatusText(betViewHolder.getMatchStatus(),"HT");
        setMatchStatusViewColor(betViewHolder.getMatchStatus());

        setResult(betViewHolder.getResult1(),
                bet.getMatch().getScore().getFullTime().getHomeTeam());
        setResultViewColor(betViewHolder.getResult1(), Color.RED);

        setResult(betViewHolder.getResult2(),
                bet.getMatch().getScore().getFullTime().getAwayTeam());
        setResultViewColor(betViewHolder.getResult2(), Color.RED);

        setItemBackgroundColorPink(betViewHolder.itemView);
    }
}
