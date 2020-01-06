package com.siemanejro.siemanejroproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemanejro.siemanejroproject.R;

import com.siemanejro.siemanejroproject.dataBinders.DataBinder;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetDrawer;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetFinishedDrawer;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetInPlayDrawer;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetPausedDrawer;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetPostponedDrawer;
import com.siemanejro.siemanejroproject.utils.betDrawerUtil.DefaultBetDrawer;
import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;
import com.siemanejro.siemanejroproject.viewHolders.LeagueViewHolder;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.siemanejro.siemanejroproject.model.BetPageItem;
import com.siemanejro.siemanejroproject.model.Status;

public class BetDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<DataBinder> binderList;

    public BetDataAdapter(List<DataBinder> binderList) {
        this.binderList = binderList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case BetPageItem.TYPE_LEAGUE: { //league name layout
                view = inflater.inflate(R.layout.league_item, viewGroup, false);
                return new LeagueViewHolder(view);
            }
            case BetPageItem.TYPE_BET : {   //bet layout
                view = inflater.inflate(R.layout.match_item, viewGroup, false);
                return new BetViewHolder(view);
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        DataBinder binder = binderList.get(position);
        binder.displayData(viewHolder);
    }

    @Override
    public int getItemCount() {
        return binderList.size();
    }

    @Override
    public int getItemViewType(int position) {
        DataBinder binder = binderList.get(position);
        return binder.getItemViewType();
    }
}
