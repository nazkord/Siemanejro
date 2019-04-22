package com.siemanejro.siemanejroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class ChooseLeague extends AppCompatActivity  {

    ListView listOfLeagues;
    ArrayAdapter<Leagues> leaguesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_league);

        listOfLeagues = (ListView) findViewById(R.id.leagues_list);
        ArrayList<Leagues> arrayOfLeagues = new ArrayList<Leagues>(Arrays.asList(Leagues.values()));
        leaguesArrayAdapter = new LeaguesAdapter(this, arrayOfLeagues);
        listOfLeagues.setAdapter(leaguesArrayAdapter);

        addListenerToListView(listOfLeagues);
    }

    private void addListenerToListView(ListView listView) {
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent  = new Intent(ChooseLeague.this, Tipp.class);
                Leagues selectedLeague = (Leagues) adapterView.getAdapter().getItem(i);
                intent.putExtra("leagueID",selectedLeague.getLeagueId());
                intent.putExtra("leagueName",selectedLeague.getUiLeagueName());
                startActivity(intent);
            }
        });
    }
}

