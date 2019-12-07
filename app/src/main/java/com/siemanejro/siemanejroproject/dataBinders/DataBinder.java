package com.siemanejro.siemanejroproject.dataBinders;

import android.support.v7.widget.RecyclerView;

import com.siemanejro.siemanejroproject.utils.betDrawerUtil.BetDrawer;

import java.util.Map;

import com.siemanejro.siemanejroproject.model.Status;

public abstract class DataBinder {

        public abstract void displayData(RecyclerView.ViewHolder viewHolder, Map<Status, BetDrawer> immutableDrawersMap);

        public abstract int getItemViewType();
}

