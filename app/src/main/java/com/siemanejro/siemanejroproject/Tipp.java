package com.siemanejro.siemanejroproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Match;

public class Tipp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp);

        ListView listView;
        listView = (ListView)findViewById(R.id.matches_list);

        MatchesAdapter matchesAdapter;

        Match match_test = new Match("2137","Walaszek","Krzysiu");
        Match match_test_2 = new Match("2138","Uaaa","XD");
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

        matchesAdapter = new MatchesAdapter(this,arrayList);
        listView.setAdapter(matchesAdapter);


    }
}
