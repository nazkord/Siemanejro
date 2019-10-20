package com.siemanejro.siemanejroproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import model.Leagues;
import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

public class LeaguesAdapter extends ArrayAdapter<Leagues> {
    private Context context;
    private ArrayList<Leagues> leagues;

    public LeaguesAdapter(Context context, ArrayList<Leagues> leagues) {
        super(context, 0, leagues);
        this.leagues = leagues;
        this.context = context;
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            //TODO: make name of league appears on the left

            convertView = inflater.inflate(R.layout.league_item, parent, false);
        }

        Leagues league = leagues.get(position);

        TextView nameOfLeague = convertView.findViewById(R.id.nameOfLeague);
        nameOfLeague.setText(league.getUiLeagueName());

        return convertView;
    }
}
