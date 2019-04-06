package com.siemanejro.siemanejroproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import model.AllMatches;
import model.Match;

public class Tipp extends AppCompatActivity {

    Button saveButton;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView;
        listView = (ListView)findViewById(R.id.matches_list);
        saveButton = (Button) findViewById(R.id.saveButton);

        MatchesAdapter matchesAdapter;
        /*Match match_test = new Match(LocalDateTime.parse("2137-02-20 12:23",format),"Walaszek","Krzysiu");
        Match match_test_2 = new Match(LocalDateTime.parse("2131-02-20 10:23",format),"Stoch","Ty Kurwo");*/
        /*
        TextView date = findViewById(R.id.date);
        date.setText(match_test.getMatchDate());

        TextView teamName1 = findViewById(R.id.teamName1);
        teamName1.setText(match_test.getTeam1());

        TextView teamName2 = findViewById(R.id.teamName2);
        teamName2.setText(match_test.getTeam2());
        */
        ArrayList<Match> arrayList = AllMatches.getStaticListOfMatches();


        matchesAdapter = new MatchesAdapter(this,arrayList);
        listView.setAdapter(matchesAdapter);


    }


}
