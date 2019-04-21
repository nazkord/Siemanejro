package com.siemanejro.siemanejroproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import json.JsonService;
import model.AllMatches;
import model.Match;

public class Tipp extends AppCompatActivity {

    Button saveButton;
    Button chooseDateButton;
    String selectedDate;
    MatchesAdapter matchesAdapter;
    ListView listView;
    ArrayList<Match> listOfMatches;
    String leagueID;
    String leagueName;
    AllMatches allMatches;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp);

        Intent intent = getIntent();
        leagueID = intent.getStringExtra("leagueID");
        leagueName = intent.getStringExtra("leagueName");

        setToolbarTitleAndBackPressButton(leagueName);

        JsonService jsonService = new JsonService();

        allMatches = jsonService.importMatchesFPM(leagueID);

        listView = (ListView)findViewById(R.id.matches_list);
        listView = (ListView) findViewById(R.id.matches_list);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButtonClicked();

        chooseDateButton = findViewById(R.id.choose_date_button);


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateTime = LocalDateTime.now().format(dateFormat);
        selectedDate = currentDateTime;
        listOfMatches = allMatches.getMatchesFromGivenDate(currentDateTime);
        matchesAdapter = new MatchesAdapter(this, listOfMatches);
        listView.setAdapter(matchesAdapter);

        chooseDateClicked();
    }

    private void setToolbarTitleAndBackPressButton(String title) {
        getSupportActionBar().setTitle(title); // set title for toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true); //enable back press button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void saveButtonClicked() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(Tipp.this,"Data Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void chooseDateClicked() {
        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog();
            }
        });

    }

    private void openDatePickerDialog() {
        Integer year = Integer.valueOf(selectedDate.substring(0,4));
        Integer monthOfYear = Integer.valueOf(selectedDate.substring(5,7)) - 1;
        Integer dayOfMonth = Integer.valueOf(selectedDate.substring(8,10));

        // open dateDialogPicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String monthString = ((Integer)(monthOfYear + 1)).toString();
                String dayString = ((Integer)dayOfMonth).toString();

                String dateInString = ((Integer)year).toString() + "-"
                        + makeStringHaveTwoDigits(monthString) + "-"
                        + makeStringHaveTwoDigits(dayString);

                modifyListOfMatchesByDate(dateInString);
                selectedDate = dateInString;
            }
        }, year, monthOfYear, dayOfMonth);
        datePickerDialog.show();

    }

    private String makeStringHaveTwoDigits(String string) {
        if(string.length() < 2) {
            return "0" + string;
        }
        return string;
    }

    private void modifyListOfMatchesByDate(String dateInString) {
        listOfMatches = allMatches.getMatchesFromGivenDate(dateInString);
        matchesAdapter.clear();
        matchesAdapter.addAll(listOfMatches);
        matchesAdapter.notifyDataSetChanged();
    }
}
