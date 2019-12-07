package com.siemanejro.siemanejroproject.adapters.BetDrawerUtil;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalTime;

import model.Bet;
import model.Match;

public abstract class BetDrawer {

    void setHomeTeamViewColor(EditText homeTeam) {
        homeTeam.setTextColor(android.graphics.Color.RED);
    }

    void setAwayTeamViewColor(EditText awayTeam) {
        awayTeam.setTextColor(android.graphics.Color.RED);
    }

    void setHomeTeamResult(EditText homeTeam, Integer score) {
        homeTeam.setText(String.valueOf(score));
        homeTeam.setFocusable(false);
    }

    void setAwayTeamResult(EditText awayTeam, Integer score) {
        awayTeam.setText(String.valueOf(score));
        awayTeam.setFocusable(false);
    }

    void setMatchStatusText(TextView matchStatus, String s) {
        matchStatus.setText(s);
    }

    void setMatchStatusTime(TextView matchStatus, Match match) {
        String s = getMinuteOfMatch(match) + "'";
        matchStatus.setText(s);
    }

    void setMatchStatusViewColor(TextView matchStatus) {
        matchStatus.setTextColor(Color.RED);
    }

    void setBoldFontToWinner(TextView team1, TextView team2, String winner) {
        switch (winner) {
            case "HOME_TEAM" : {
                team1.setTypeface(null, Typeface.BOLD);
                break;
            }
            case "AWAY_TEAM" : {
                team2.setTypeface(null, Typeface.BOLD);
                break;
            }
        }
    }

    void setItemBackgroundColorPink(View itemView) {
        itemView.setBackgroundColor(Color.rgb(255,230,238));
    }

    private Long getMinuteOfMatch(Match match) {
        LocalTime matchTime = LocalTime.parse(match.getUtcDate().substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }

    public abstract void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet);

}
