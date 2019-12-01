package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.adapters.BetViewHolder;

import java.time.Duration;
import java.time.LocalTime;

import model.Match;

public abstract class BetDrawer {
    Match currentMatch;
    private View itemView;
    private TextView matchStatus;
    private EditText result1;
    private EditText result2;
    TextView team1;
    TextView team2;
    TextView date;

    BetDrawer(RecyclerView.ViewHolder holder, Match match) {
        BetViewHolder betViewHolder = (BetViewHolder) holder;
        itemView = betViewHolder.itemView;
        this.currentMatch = match;
        this.matchStatus = betViewHolder.getMatchStatus();
        this.result1 = betViewHolder.getResult1();
        this.result2 = betViewHolder.getResult2();
        this.team1 = betViewHolder.getTeam1();
        this.team2 = betViewHolder.getTeam2();
        this.date = betViewHolder.getDate();
    }

    void setHomeTeamViewColor() {
        result1.setTextColor(android.graphics.Color.RED);
    }

    void setAwayTeamViewColor() {
        result2.setTextColor(android.graphics.Color.RED);
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

    void setItemBackgroundColorPink() {
        itemView.setBackgroundColor(Color.rgb(255,230,238));
    }

    Long getMinuteOfMatch() {
        LocalTime matchTime = LocalTime.parse(currentMatch.getUtcDate().substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }

    public abstract void drawBet();
}
