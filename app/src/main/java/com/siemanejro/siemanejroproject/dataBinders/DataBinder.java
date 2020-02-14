package com.siemanejro.siemanejroproject.dataBinders;

import androidx.recyclerview.widget.RecyclerView;

public abstract class DataBinder {

        public abstract void displayData(RecyclerView.ViewHolder viewHolder);

        public abstract int getItemViewType();
}

