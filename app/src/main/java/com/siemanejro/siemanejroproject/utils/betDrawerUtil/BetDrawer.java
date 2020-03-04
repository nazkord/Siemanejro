package com.siemanejro.siemanejroproject.utils.betDrawerUtil;

import android.graphics.Color;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.Status;

public abstract class BetDrawer {

    public static Map<Status, BetDrawer> immutableDrawersMap = Collections.unmodifiableMap(
            new LinkedHashMap<>(new HashMap<Status, BetDrawer>() {{
                put(Status.POSTPONED, new BetPostponedDrawer());
                put(Status.IN_PLAY, new BetInPlayDrawer());
                put(Status.PAUSED, new BetPausedDrawer());
                put(Status.FINISHED, new BetFinishedDrawer());
            }})
    );

    void setMatchDate(TextView date, String dateInString) {
        String finalText = dateInString.substring(0, 10) + " " + dateInString.substring(11, 16);
        date.setText(finalText);
    }

    void setTeamName(TextView team, String teamName) {
        team.setText(teamName);
        team.setTypeface(null, Typeface.NORMAL);
    }

    void setResultViewColor(EditText awayTeam, int color) {
        awayTeam.setTextColor(color);
    }

    void setResult(EditText team, Integer score) {
        team.setText(String.valueOf(score));
        team.setFocusable(false);
    }

    void setDefaultMatchStatusLayout(TextView matchStatusLayout) {
        matchStatusLayout.setText(null);
        matchStatusLayout.setTextColor(Color.GRAY);
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

    void setDefaultItemBackgroundColor(View itemView) {
        itemView.setBackgroundColor(Color.WHITE);
    }

    private Long getMinuteOfMatch(Match match) {
        LocalTime matchTime = LocalTime.parse(match.getUtcDate().substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }

    public abstract void drawBet(RecyclerView.ViewHolder viewHolder, Bet bet);

}
