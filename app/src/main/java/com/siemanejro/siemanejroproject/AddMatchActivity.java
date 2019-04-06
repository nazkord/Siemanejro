package com.siemanejro.siemanejroproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import model.Match;

public class AddMatchActivity extends AppCompatActivity {

    EditText input_date;
    EditText input_time;

    // for chosing date in better way
    Calendar calendarForDialog;
    DatePickerDialog datePickerDialog;

    EditText input_nameFirstTeam;
    EditText input_nameSecondTeam;
    Button addButton;

    //our dates of match pattern
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    //temporary (in future use of Database)
    ArrayList<Match> matchesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        addToolbar();
        matchesArray = new ArrayList<>();
        input_date = (EditText) findViewById(R.id.input_date);
        input_time = (EditText) findViewById(R.id.input_time);

        inputDateClicked();

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
                String nameFirstTeam = input_nameFirstTeam.getText().toString();
                String nameSecondTeam = input_nameSecondTeam.getText().toString();
                Match matchTemp = new Match(convertStringDates(),nameFirstTeam,nameSecondTeam);
                matchesArray.add(matchTemp);
                input_date.setText("");
                input_time.setText("");
                input_nameFirstTeam.setText("");
                input_nameSecondTeam.setText("");
            }
        });
    }

    private LocalDateTime convertStringDates() {
        String dateMatch = input_date.getText().toString();
        String timeMatch = input_time.getText().toString();

        String dateTimeMatch = dateMatch + " " + timeMatch;
        return LocalDateTime.parse(dateTimeMatch, dateTimeFormatter);
    }

    private void inputDateClicked() {
        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarForDialog = Calendar.getInstance();

                int year = calendarForDialog.get(Calendar.YEAR);
                int monthOfYear = calendarForDialog.get(Calendar.MONTH);
                int dayOfMonth = calendarForDialog.get(Calendar.DAY_OF_MONTH);

                // open dateDialogPicker
                datePickerDialog = new DatePickerDialog(AddMatchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        input_date.setText(year + "-0" + (monthOfYear+1) + "-" + dayOfMonth);
                    }
                }, year, monthOfYear, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }
}
