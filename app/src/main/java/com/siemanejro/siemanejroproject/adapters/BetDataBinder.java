package com.siemanejro.siemanejroproject.adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.adapters.BetUtil.BetFinishedDrawer;

import java.time.Duration;
import java.time.LocalTime;

import model.Bet;
import model.FullTimeResult;
import model.Match;
import model.BetPageItem;
import model.Status;

//TODO: each switch -> one method; make abstract class for those methods; create map (status -> method)

public class BetDataBinder extends DataBinder {


    private final Bet bet;

    public BetDataBinder(Bet bet) {
        this.bet = bet;
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public void displayData(RecyclerView.ViewHolder holder) {
        BetViewHolder viewHolder = (BetViewHolder) holder;
        Match currentMatch = bet.getMatch();

        TextView matchStatus = viewHolder.getMatchStatus();
        EditText result1 = viewHolder.getResult1();
        EditText result2 = viewHolder.getResult2();
        TextView team1 = viewHolder.getTeam1();
        TextView team2 = viewHolder.getTeam2();
        TextView date = viewHolder.getDate();

        //set status of match
        matchStatus.setText(null);

        //set date of match
        //TODO: should be done this zonedDateTime
        String text = bet.getMatch().getUtcDate();
        String finalText = text.substring(0,10)+" "+text.substring(11,16);
        date.setText(finalText);

        //set name of teams
        team1.setText(bet.getMatch().getHomeTeam().getName());
        team1.setTypeface(null, Typeface.NORMAL);
        team2.setText(bet.getMatch().getAwayTeam().getName());
        team2.setTypeface(null, Typeface.NORMAL);

        // set default results view
        result1.setTextColor(Color.BLACK);
        result2.setTextColor(Color.BLACK);

//        FullTimeResult fullTimeUserBet = bet.getUserScore().getFullTime();
//        if(fullTimeUserBet.getHomeTeam() == null || fullTimeUserBet.getAwayTeam() == null) {
//        result1.setText(null);
//        result2.setText(null);
//        } else {
//            result1.setText(bet.getUserScore().getFullTime().getHomeTeam());
//            result2.setText(bet.getUserScore().getFullTime().getAwayTeam());
//        }

        result1.setText(null);
        result2.setText(null);
        result1.setFocusableInTouchMode(true);
        result2.setFocusableInTouchMode(true);


        ///setBackgroundColorToDefault
        viewHolder.itemView.setBackgroundColor(Color.WHITE);

        switch (Status.valueOf(currentMatch.getStatus())) {
            case POSTPONED: {
                matchStatus.setText("POSTPONED");
            }
            case IN_PLAY : {
                result1.setFocusable(false);
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
                result1.setTextColor(Color.RED);

                result2.setFocusable(false);
                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
                result2.setTextColor(Color.RED);

                String matchMinute = getMinuteOfMatch(currentMatch.getUtcDate()) + "'";
                matchStatus.setText(matchMinute);
                matchStatus.setTextColor(Color.RED);

                switch (currentMatch.getScore().getWinner()) {
                    case "HOME_TEAM" : {
                        team1.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                    case "AWAY_TEAM" : {
                        team2.setTypeface(null, Typeface.BOLD);
                        break;
                    }
                }

                viewHolder.itemView.setBackgroundColor(Color.rgb(255,230,238));
                break;
            }
            case PAUSED: {
                matchStatus.setText("HF");
                matchStatus.setTextColor(Color.RED);

                result1.setFocusable(false);
                result1.setText(String.valueOf(currentMatch.getScore().getFullTime().getHomeTeam()));
                result1.setTextColor(Color.RED);

                result2.setFocusable(false);
                result2.setText(String.valueOf(currentMatch.getScore().getFullTime().getAwayTeam()));
                result2.setTextColor(Color.RED);

                break;
            }
            case FINISHED: {
                new BetFinishedDrawer(viewHolder, currentMatch).drawBet();
            }
        }
    }


    @Override
    public int getItemViewType() {
        return BetPageItem.TYPE_BET;
    }

    private Long getMinuteOfMatch(String matchTimeString) {
        LocalTime matchTime = LocalTime.parse(matchTimeString.substring(11,16));
        return Duration.between(matchTime, LocalTime.now()).toMinutes();
    }
}
