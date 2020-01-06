package com.siemanejro.siemanejroproject.dataBinders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.BetPageItem;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.Status;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetDrawer;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.DefaultBetDrawer;
import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;

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
    public void displayData(RecyclerView.ViewHolder holder) {

        BetViewHolder betViewHolder = (BetViewHolder) holder;
        EditText result1 = betViewHolder.getResult1();
        EditText result2 = betViewHolder.getResult2();

        //defaultDrawingOfBet
        new DefaultBetDrawer().drawBet(betViewHolder, bet);

        //drawBet
        Optional.ofNullable(BetDrawer.immutableDrawersMap.get(Status.valueOf(bet.getMatch().getStatus())))
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
