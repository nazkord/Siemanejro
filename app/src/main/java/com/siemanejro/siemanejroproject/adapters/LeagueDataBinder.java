package com.siemanejro.siemanejroproject.adapters;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetDrawer;

import java.util.Map;

import model.BetPageItem;
import model.League;
import model.Status;

public class LeagueDataBinder extends DataBinder {

    private final League league;

    public LeagueDataBinder(League league) {
        this.league = league;
    }

    public League getLeague() {
        return league;
    }

    @Override
    public void displayData(RecyclerView.ViewHolder holder, Map<Status, BetDrawer> immutableDrawersMap) {
        LeagueViewHolder viewHolder = (LeagueViewHolder) holder;

        viewHolder.getLeagueLogo().setImageResource(league.getLeagueIcon());
        viewHolder.getLeagueName().setText(league.getLeagueName());
    }

    @Override
    public int getItemViewType() {
        return BetPageItem.TYPE_LEAGUE;
    }
}
