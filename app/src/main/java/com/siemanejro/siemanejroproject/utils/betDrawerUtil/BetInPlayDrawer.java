package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import com.siemanejro.siemanejroproject.model.Bet;

public class BetInPlayDrawer extends BetDrawer {

    @Override
    public void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet) {
        BetViewHolder betViewHolder = (BetViewHolder) viewHolder;
        setResult(betViewHolder.getResult1(),
                bet.getMatch().getScore().getFullTime().getHomeTeam());
        setResultViewColor(betViewHolder.getResult1(), Color.RED);

        setResult(betViewHolder.getResult2(),
                bet.getMatch().getScore().getFullTime().getAwayTeam());
        setResultViewColor(betViewHolder.getResult2(), Color.RED);

        setMatchStatusTime(betViewHolder.getMatchStatus(), bet.getMatch());
        setMatchStatusViewColor(betViewHolder.getMatchStatus());

        setBoldFontToWinner(betViewHolder.getTeam1(), betViewHolder.getTeam2(),
                bet.getMatch().getScore().getWinner());

        setItemBackgroundColorPink(betViewHolder.itemView);
    }

}
