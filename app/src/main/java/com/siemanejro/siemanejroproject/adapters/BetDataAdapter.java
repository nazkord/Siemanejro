package com.siemanejro.siemanejroproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;
import java.util.List;

import model.RVItems.BetPageItem;

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
