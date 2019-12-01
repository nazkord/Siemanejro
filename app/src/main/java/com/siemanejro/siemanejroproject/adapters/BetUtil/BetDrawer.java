package com.siemanejro.siemanejroproject.adapters.BetUtil;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.adapters.BetViewHolder;

import model.Match;

public abstract class BetDrawer {
    Match currentMatch;
    private TextView matchStatus;
    private EditText result1;
    private EditText result2;
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

    void setHomeTeamViewColor(int color) {
        result1.setTextColor(color);
    }

    void setAwatTeamViewColor(int color) {
        result2.setTextColor(color);
    }

    void setHomeTeamResult() {
        result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
        result1.setFocusable(false);
    }

    void setAwayTeamResult() {
        result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
        result2.setFocusable(false);
    }

    void setMatchStatusText(String s) {
        matchStatus.setText(s);
    }

    void setMatchStatusViewColor(int color) {
        matchStatus.setTextColor(color);
    }

    public abstract void drawBet();
}
