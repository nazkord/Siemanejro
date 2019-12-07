package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.adapters.BetViewHolder;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

import model.Bet;
import model.Match;

public abstract class BetDrawer {
    private BetViewHolder betViewHolder;
    private Match currentMatch;
    private View itemView;
    private TextView matchStatus;
    private EditText result1;
    private EditText result2;
    TextView team1;
    TextView team2;
    TextView date;

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void setBetViewHolder(RecyclerView.ViewHolder viewHolder) {
        betViewHolder = (BetViewHolder) viewHolder;
        itemView = betViewHolder.itemView;
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

    void setMatchStatusViewColor() {
        matchStatus.setTextColor(Color.RED);
    }

    void setItemBackgroundColorPink() {
        itemView.setBackgroundColor(Color.rgb(255,230,238));
    }

    Long getMinuteOfMatch() {
        LocalTime matchTime = LocalTime.parse(currentMatch.getUtcDate().substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }

    public abstract void drawBet(Bet bet);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetDrawer betDrawer = (BetDrawer) o;
        return Objects.equals(betViewHolder, betDrawer.betViewHolder) &&
                Objects.equals(currentMatch, betDrawer.currentMatch) &&
                Objects.equals(itemView, betDrawer.itemView) &&
                Objects.equals(matchStatus, betDrawer.matchStatus) &&
                Objects.equals(result1, betDrawer.result1) &&
                Objects.equals(result2, betDrawer.result2) &&
                Objects.equals(team1, betDrawer.team1) &&
                Objects.equals(team2, betDrawer.team2) &&
                Objects.equals(date, betDrawer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betViewHolder, currentMatch, itemView, matchStatus, result1, result2, team1, team2, date);
    }
}
