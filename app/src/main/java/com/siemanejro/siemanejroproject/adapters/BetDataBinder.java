package com.siemanejro.siemanejroproject.adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetFinishedDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetInPlayDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetPausedDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetPostponedDrawer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.Bet;
import model.BetPageItem;
import model.Match;
import model.Status;

//TODO: is it okay? second: split into default methods?

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

        BetViewHolder betViewHolder = (BetViewHolder) holder;
        Match currentMatch = bet.getMatch();

        final Map<Status, BetDrawer> drawerMap = new HashMap<Status, BetDrawer>() {{
            put(Status.POSTPONED, new BetPostponedDrawer(betViewHolder, currentMatch));
            put(Status.IN_PLAY, new BetInPlayDrawer(betViewHolder, currentMatch));
            put(Status.PAUSED, new BetPausedDrawer(betViewHolder, currentMatch));
            put(Status.FINISHED, new BetFinishedDrawer(betViewHolder, currentMatch));
        }};

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
        result1.setText(null);
        result2.setText(null);
        result1.setFocusableInTouchMode(true);
        result2.setFocusableInTouchMode(true);


        ///setBackgroundColorToDefault
        betViewHolder.itemView.setBackgroundColor(Color.WHITE);

        //drawBet
        Optional.ofNullable(drawerMap.get(Status.valueOf(currentMatch.getStatus())))
                .ifPresent(BetDrawer::drawBet);
    }

    @Override
    public int getItemViewType() {
        return BetPageItem.TYPE_BET;
    }
}
