package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetFinishedDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setHomeTeamResult(betViewHolder.getResult1(), bet.getMatch().getScore().getFullTime().getHomeTeam());
        setAwayTeamResult(betViewHolder.getResult2(), bet.getMatch().getScore().getFullTime().getAwayTeam());
        setMatchStatusText(betViewHolder.getMatchStatus(),"FT");
        setBoldFontToWinner(betViewHolder.getTeam1(), betViewHolder.getTeam2(),
                bet.getMatch().getScore().getWinner());
    }
}
