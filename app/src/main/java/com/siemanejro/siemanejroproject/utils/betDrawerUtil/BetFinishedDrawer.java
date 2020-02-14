package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetFinishedDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setResult(betViewHolder.getResult1(), bet.getMatch().getScore().getFullTime().getHomeTeam());
        setResult(betViewHolder.getResult2(), bet.getMatch().getScore().getFullTime().getAwayTeam());
        setResultViewColor(betViewHolder.getResult1(), Color.BLACK);
        setResultViewColor(betViewHolder.getResult2(), Color.BLACK);
        setMatchStatusText(betViewHolder.getMatchStatus(),"FT");
        setBoldFontToWinner(betViewHolder.getTeam1(), betViewHolder.getTeam2(),
                bet.getMatch().getScore().getWinner());
    }
}
