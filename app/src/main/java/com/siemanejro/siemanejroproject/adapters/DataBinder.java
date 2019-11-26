package com.siemanejro.siemanejroproject.adapters;

import android.support.v7.widget.RecyclerView;

public abstract class DataBinder {

        public abstract void displayData(RecyclerView.ViewHolder viewHolder);

        public abstract int getItemViewType();
}

