package com.siemanejro.siemanejroproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import model.RVItems.League;

import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;

public class LeaguesAdapter extends ArrayAdapter {


    private Context context;
    private ArrayList<League> leagues;
    private static final ArrayList<Integer> logos = new ArrayList<Integer>() {
        {
            add(R.drawable.ic_bundesliga);
            add(R.drawable.ic_pl1);
            add(R.drawable.ic_laliga1);
            add(R.drawable.ic_serie_a);
            add(R.drawable.ic_ligue_1);
        }
    };

    public LeaguesAdapter(Context context, ArrayList<League> leagues) {
        super(context, 0, leagues);
        this.leagues = leagues;
        this.context = context;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.league_item, parent, false);
        }

        League league = leagues.get(position);

        TextView nameOfLeague = convertView.findViewById(R.id.nameOfLeague);
        nameOfLeague.setText(league.getLeagueName());

        ImageView leagueLogo = convertView.findViewById(R.id.imageOfLeague);
        leagueLogo.setImageResource(logos.get(position));

        return convertView;
    }
}
