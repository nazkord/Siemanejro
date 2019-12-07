package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetPausedDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setMatchStatusText(betViewHolder.getMatchStatus(),"HT");
        setMatchStatusViewColor(betViewHolder.getMatchStatus());

        setHomeTeamResult(betViewHolder.getResult1(),
                bet.getMatch().getScore().getFullTime().getHomeTeam());
        setHomeTeamViewColor(betViewHolder.getResult1());

        setAwayTeamResult(betViewHolder.getResult2(),
                bet.getMatch().getScore().getFullTime().getAwayTeam());
        setAwayTeamViewColor(betViewHolder.getResult2());

        setItemBackgroundColorPink(betViewHolder.itemView);
    }
}
