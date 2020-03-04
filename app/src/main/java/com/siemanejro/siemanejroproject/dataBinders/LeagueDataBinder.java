package com.siemanejro.siemanejroproject.dataBinders;

import androidx.recyclerview.widget.RecyclerView;

import com.siemanejro.siemanejroproject.viewHolders.LeagueViewHolder;

import java.util.Objects;

import com.siemanejro.siemanejroproject.model.BetPageItem;
import com.siemanejro.siemanejroproject.model.League;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueDataBinder that = (LeagueDataBinder) o;
        return league == that.league;
    }

    @Override
    public int hashCode() {
        return Objects.hash(league);
    }
}
