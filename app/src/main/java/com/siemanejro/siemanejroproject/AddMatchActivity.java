package com.siemanejro.siemanejroproject;

import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import model.Match;

public class AddMatchActivity extends AppCompatActivity {

    EditText input_date;
    EditText input_nameFirstTeam;
    EditText input_nameSecondTeam;
    Button addButton;
    ArrayList<Match> matchesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        addToolbar();

        input_date = (EditText) findViewById(R.id.input_date);
        input_nameFirstTeam = (EditText) findViewById(R.id.input_first_team);
        input_nameSecondTeam = (EditText) findViewById(R.id.input_second_team);
        addButton = (Button) findViewById(R.id.addButton);

        addButtonClicked();

    }

    private void addToolbar() {
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbarAddMatch);
        setSupportActionBar(Toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Match");
        }
    }

    private void addButtonClicked() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateMatch = input_date.getText().toString();
                String nameFirstTeam = input_nameFirstTeam.getText().toString();
                String nameSecondTeam = input_nameSecondTeam.getText().toString();
                matchesArray.add(new Match(dateMatch,nameFirstTeam,nameSecondTeam));
                input_date.setText("");
                input_nameFirstTeam.setText("");
                input_nameSecondTeam.setText("");
            }
        });
    }

}
