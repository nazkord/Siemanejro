package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetInPlayDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setHomeTeamResult(betViewHolder.getResult1(),
                bet.getMatch().getScore().getFullTime().getHomeTeam());
        setHomeTeamViewColor(betViewHolder.getResult1());

        setAwayTeamResult(betViewHolder.getResult2(),
                bet.getMatch().getScore().getFullTime().getAwayTeam());
        setAwayTeamViewColor(betViewHolder.getResult2());

        setMatchStatusTime(betViewHolder.getMatchStatus(), bet.getMatch());
        setMatchStatusViewColor(betViewHolder.getMatchStatus());

        setBoldFontToWinner(betViewHolder.getTeam1(), betViewHolder.getTeam2(),
                bet.getMatch().getScore().getWinner());

        setItemBackgroundColorPink(betViewHolder.itemView);
    }

}
