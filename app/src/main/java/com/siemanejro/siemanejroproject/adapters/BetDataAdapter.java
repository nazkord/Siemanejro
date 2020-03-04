package com.siemanejro.siemanejroproject.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemanejro.siemanejroproject.R;

import com.siemanejro.siemanejroproject.dataBinders.DataBinder;
import com.siemanejro.siemanejroproject.viewHolders.BetViewHolder;
import com.siemanejro.siemanejroproject.viewHolders.LeagueViewHolder;

import java.util.List;

import com.siemanejro.siemanejroproject.model.BetPageItem;

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
