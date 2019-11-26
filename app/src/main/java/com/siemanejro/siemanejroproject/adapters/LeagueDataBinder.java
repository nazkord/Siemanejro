package com.siemanejro.siemanejroproject.adapters;

import android.support.v7.widget.RecyclerView;

import model.RVItems.BetPageItem;
import model.RVItems.League;

public class LeagueDataBinder extends DataBinder {

    private final League league;

    public LeagueDataBinder(League league) {
        this.league = league;
    }

    public League getLeague() {
        return league;
    }

    @Override
    public void displayData(RecyclerView.ViewHolder holder) {
        LeagueViewHolder viewHolder = (LeagueViewHolder) holder;

        viewHolder.getLeagueLogo().setImageResource(league.getLeagueIcon());
        viewHolder.getLeagueName().setText(league.getLeagueName());
    }

    @Override
    public int getItemViewType() {
        return BetPageItem.TYPE_LEAGUE;
    }
}
