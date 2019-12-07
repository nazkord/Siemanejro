package com.siemanejro.siemanejroproject.adapters;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.adapters.BetDrawerUtil.BetDrawer;

import java.util.Map;

import model.Status;

public abstract class DataBinder {

        public abstract void displayData(RecyclerView.ViewHolder viewHolder, Map<Status, BetDrawer> immutableDrawersMap);

        public abstract int getItemViewType();
}

