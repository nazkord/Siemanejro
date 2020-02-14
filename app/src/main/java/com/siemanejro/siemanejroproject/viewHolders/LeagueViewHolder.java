package com.siemanejro.siemanejroproject.viewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

public class LeagueViewHolder extends RecyclerView.ViewHolder {
    private ImageView leagueLogo;
    private TextView leagueName;

    public LeagueViewHolder(@NonNull View itemView) {
        super(itemView);
        leagueLogo = itemView.findViewById(R.id.imageOfLeague);
        leagueName = itemView.findViewById(R.id.nameOfLeague);
    }

    public ImageView getLeagueLogo() {
        return leagueLogo;
    }

    public TextView getLeagueName() {
        return leagueName;
    }
}
