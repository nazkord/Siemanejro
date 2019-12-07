package com.siemanejro.siemanejroproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemanejro.siemanejroproject.R;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetFinishedDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetInPlayDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetPausedDrawer;
import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetPostponedDrawer;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Bet;
import model.BetPageItem;
import model.Status;

public class BetDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<DataBinder> binderList;

    private Map<Status, BetDrawer> drawersMap = new HashMap<Status, BetDrawer>(
    ) {{
        put(Status.POSTPONED, new BetPostponedDrawer());
        put(Status.IN_PLAY, new BetInPlayDrawer());
        put(Status.PAUSED, new BetPausedDrawer());
        put(Status.FINISHED, new BetFinishedDrawer());
    }};

    private Map<Status, BetDrawer> immutableDrawersMap = Collections.unmodifiableMap(
            new LinkedHashMap<>(drawersMap)
    );

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
        binder.displayData(viewHolder, immutableDrawersMap);
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
