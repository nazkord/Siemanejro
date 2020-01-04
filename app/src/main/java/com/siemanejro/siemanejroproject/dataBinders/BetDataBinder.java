package com.siemanejro.siemanejroproject.dataBinders;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.BetPageItem;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.Status;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetDrawer;
import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BetDataBinder extends DataBinder {

    private final Bet bet;
    private HomeTeamFocusListener homeTeamFocusListener;
    private AwayTeamFocusListener awayTeamFocusListener;

    public BetDataBinder(Bet bet) {
        this.bet = bet;
        this.homeTeamFocusListener = new HomeTeamFocusListener();
        this.awayTeamFocusListener = new AwayTeamFocusListener();
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public void displayData(RecyclerView.ViewHolder holder, Map<Status, BetDrawer> drawersMap) {

        BetViewHolder betViewHolder = (BetViewHolder) holder;
        Match currentMatch = bet.getMatch();
        TextView matchStatus = betViewHolder.getMatchStatus();
        EditText result1 = betViewHolder.getResult1();
        EditText result2 = betViewHolder.getResult2();
        TextView team1 = betViewHolder.getTeam1();
        TextView team2 = betViewHolder.getTeam2();
        TextView date = betViewHolder.getDate();

        //set status of match
        matchStatus.setText(null);
        matchStatus.setTextColor(Color.GRAY);

        //set date of match
        //TODO: should be done this zonedDateTime
        String text = bet.getMatch().getUtcDate();
        String finalText = text.substring(0, 10) + " " + text.substring(11, 16);
        date.setText(finalText);

        //set name of teams
        team1.setText(bet.getMatch().getHomeTeam().getName());
        team1.setTypeface(null, Typeface.NORMAL);
        team2.setText(bet.getMatch().getAwayTeam().getName());
        team2.setTypeface(null, Typeface.NORMAL);

        // set default results view
        result1.setTextColor(Color.BLACK);
        result2.setTextColor(Color.BLACK);

        if(bet.getUserScore().getFullTime().getHomeTeam() == null) {
            result1.setText(null);
        } else {
            result1.setText(String.valueOf(bet.getUserScore().getFullTime().getHomeTeam()));
            if(!bet.getIsChanged()) {
                result1.setTextColor(Color.GRAY);
                result2.setTextColor(Color.GRAY);
            }
        }

        if(bet.getUserScore().getFullTime().getAwayTeam() == null) {
            result2.setText(null);
        } else {
            result2.setText(String.valueOf(bet.getUserScore().getFullTime().getAwayTeam()));
        }

        result1.setFocusableInTouchMode(true);
        result2.setFocusableInTouchMode(true);

        ///setBackgroundColorToDefault
        betViewHolder.itemView.setBackgroundColor(Color.WHITE);

        //drawBet
        Optional.ofNullable(drawersMap.get(Status.valueOf(currentMatch.getStatus())))
                .ifPresent(betDrawer1 -> betDrawer1.drawBet(betViewHolder, bet));

        result1.setOnFocusChangeListener(homeTeamFocusListener);
        result2.setOnFocusChangeListener(awayTeamFocusListener);
    }

    @Override
    public int getItemViewType() {
        return BetPageItem.TYPE_BET;
    }

    private class HomeTeamFocusListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText resultView = (EditText) v;
            if (!hasFocus) {
                String result = resultView.getText().toString();
                if (!result.isEmpty()) {
                    Integer homeTeamResultBet = Integer.valueOf(result);
                    bet.getUserScore().getFullTime().setHomeTeam(homeTeamResultBet);
                    bet.setChanged(true);
                }
            }
        }
    }

    private class AwayTeamFocusListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText resultView = (EditText) v;
            if (!hasFocus) {
                String result = resultView.getText().toString();
                if (!result.isEmpty()) {
                    Integer awayTeamResultBet = Integer.valueOf(result);
                    bet.getUserScore().getFullTime().setAwayTeam(awayTeamResultBet);
                    bet.setChanged(true);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetDataBinder that = (BetDataBinder) o;
        return Objects.equals(bet, that.bet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet);
    }
}
