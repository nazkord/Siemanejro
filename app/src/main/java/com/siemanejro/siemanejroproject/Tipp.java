package com.siemanejro.siemanejroproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Match match_test = new Match(LocalDateTime.parse("2137-02-20 12:23",format),"Walaszek","Krzysiu");
        Match match_test_2 = new Match(LocalDateTime.parse("2131-02-20 10:23",format),"Uaaa","XD");
        /*
        TextView date = findViewById(R.id.date);
        date.setText(match_test.getMatchDate());

        TextView teamName1 = findViewById(R.id.teamName1);
        teamName1.setText(match_test.getTeam1());

        TextView teamName2 = findViewById(R.id.teamName2);
        teamName2.setText(match_test.getTeam2());
        */

        ArrayList<Match> arrayList = new ArrayList<>();
        arrayList.add(match_test);
        arrayList.add(match_test_2);

        matchesAdapter = new MatchesAdapter(this,arrayList);
        listView.setAdapter(matchesAdapter);


    }


}
