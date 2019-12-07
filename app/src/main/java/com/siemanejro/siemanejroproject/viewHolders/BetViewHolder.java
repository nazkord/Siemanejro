package com.siemanejro.siemanejroproject.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

public class BetViewHolder extends RecyclerView.ViewHolder {
    private TextView date;
    private TextView matchStatus;
    private TextView team1;
    private TextView team2;
    private EditText result1;
    private EditText result2;

    public BetViewHolder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.dateTime);
        matchStatus = itemView.findViewById(R.id.matchStatus);
        team1 = itemView.findViewById(R.id.teamName1);
        team2 = itemView.findViewById(R.id.teamName2);
        result1 = itemView.findViewById(R.id.result1);
        result2 = itemView.findViewById(R.id.result2);
    }

    public TextView getDate() {
        return date;
    }

    public TextView getMatchStatus() {
        return matchStatus;
    }

    public TextView getTeam1() {
        return team1;
    }

    public TextView getTeam2() {
        return team2;
    }

    public EditText getResult1() {
        return result1;
    }

    public EditText getResult2() {
        return result2;
    }

}
