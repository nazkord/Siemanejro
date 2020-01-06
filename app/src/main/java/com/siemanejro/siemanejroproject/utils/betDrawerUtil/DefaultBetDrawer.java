package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

public class DefaultBetDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setDefaultItemBackgroundColor(viewHolder.itemView);
        setMatchDate(betViewHolder.getDate(), bet.getMatch().getUtcDate());
        setDefaultMatchStatusLayout(betViewHolder.getMatchStatus());

        setTeamName(betViewHolder.getTeam1(), bet.getMatch().getHomeTeam().getName());
        setTeamName(betViewHolder.getTeam2(), bet.getMatch().getAwayTeam().getName());

        if(bet.getUserScore().getFullTime().getHomeTeam() == null) {
//            setResult(betViewHolder.getResult1(), null);
            betViewHolder.getResult1().setText(null);
            betViewHolder.getResult1().setFocusableInTouchMode(true);
            setResultViewColor(betViewHolder.getResult1(), Color.BLACK);
        } else {
            setResult(betViewHolder.getResult1(), bet.getUserScore().getFullTime().getHomeTeam());
            betViewHolder.getResult1().setFocusableInTouchMode(true);
            setResultViewColor(betViewHolder.getResult1(), Color.rgb(105,105,105));
        }

        if(bet.getUserScore().getFullTime().getAwayTeam() == null) {
//            setResult(betViewHolder.getResult2(), null);
            betViewHolder.getResult2().setText(null);
            betViewHolder.getResult2().setFocusableInTouchMode(true);
            setResultViewColor(betViewHolder.getResult2(), Color.BLACK);
        } else {
            setResult(betViewHolder.getResult2(), bet.getUserScore().getFullTime().getAwayTeam());
            betViewHolder.getResult2().setFocusableInTouchMode(true);
            setResultViewColor(betViewHolder.getResult2(), Color.rgb(105,105,105));
        }
    }

}
