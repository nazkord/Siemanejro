package com.siemanejro.siemanejroproject.adapters.BetUtil;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.adapters.BetViewHolder;

import model.Match;

public abstract class BetDrawer {
    Match currentMatch;
    TextView matchStatus;
    EditText result1;
    EditText result2;
    TextView team1;
    TextView team2;
    TextView date;

    BetDrawer(RecyclerView.ViewHolder holder, Match match) {
        BetViewHolder betViewHolder = (BetViewHolder) holder;
        this.currentMatch = match;
        this.matchStatus = betViewHolder.getMatchStatus();
        this.result1 = betViewHolder.getResult1();
        this.result2 = betViewHolder.getResult2();
        this.team1 = betViewHolder.getTeam1();
        this.team2 = betViewHolder.getTeam2();
        this.date = betViewHolder.getDate();
    }

    public abstract void drawBet();
}
