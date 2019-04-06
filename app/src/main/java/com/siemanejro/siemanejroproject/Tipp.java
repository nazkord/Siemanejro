package com.siemanejro.siemanejroproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import model.AllMatches;
import model.Match;
import model.Score;

public class Tipp extends AppCompatActivity {

    Button saveButton;
    MatchesAdapter matchesAdapter;
    ListView listView;
    ArrayList<Match> listOfMatches;

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

        //to get able to come back to previous page
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.matches_list);
        saveButton = (Button) findViewById(R.id.saveButton);

        listOfMatches = AllMatches.getStaticListOfMatches();

        matchesAdapter = new MatchesAdapter(this, listOfMatches);
        listView.setAdapter(matchesAdapter);

    }

    private void saveButtonClicked() {

    }


}
