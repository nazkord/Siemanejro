package com.siemanejro.siemanejroproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.siemanejro.siemanejroproject.MatchesAdapter;
import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;
import java.util.List;

import model.Match;

public class UserBetsActivity extends AppCompatActivity {

    //TODO: make choose button or
   // Button chooseDateButton;
    String selectedDate;
    MatchesAdapter matchesAdapter;
    ListView listView;
    List<Match> listOfMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bets);

        setToolbarTitle(getString(R.string.userBetsActivityName));



    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title); // set title for toolbar
    }

}
