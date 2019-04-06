package com.siemanejro.siemanejroproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.AllMatches;
import model.Match;
import model.Score;

public class Tipp extends AppCompatActivity {

    Button saveButton;
    Button chooseDateButton;
    String date;
    ArrayList<Match> arrayList;
    MatchesAdapter matchesAdapter;
    ListView listView;
    ArrayList<Match> listOfMatches;

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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.matches_list);
        listView = (ListView) findViewById(R.id.matches_list);
        saveButton = (Button) findViewById(R.id.saveButton);

        listOfMatches = AllMatches.getStaticListOfMatches();

        matchesAdapter = new MatchesAdapter(this, listOfMatches);
        chooseDateButton = findViewById(R.id.choose_date_button);
        chooseDateClicked();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateNew = new Date();
        Log.d("DATE", dateFormat.format(dateNew));
        date = dateFormat.format(dateNew).substring(0, 10);
        arrayList = AllMatches.getMatchesFromGivenDate(date);
        matchesAdapter = new MatchesAdapter(this, arrayList);
        listView.setAdapter(matchesAdapter);


    }

    private void chooseDateClicked() {
        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendarForDialog = Calendar.getInstance();

                Integer year = calendarForDialog.get(Calendar.YEAR);
                Integer monthOfYear = calendarForDialog.get(Calendar.MONTH);
                Integer dayOfMonth = calendarForDialog.get(Calendar.DAY_OF_MONTH);

                // open dateDialogPicker
                DatePickerDialog datePickerDialog = new DatePickerDialog(Tipp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String yearString = ((Integer)year).toString();
                        monthOfYear+=1;
                        String monthString = ((Integer)monthOfYear).toString();
                        String dayString = ((Integer)dayOfMonth).toString();
                        if(monthString.length()<2) {
                            monthString="0"+monthString;
                        }
                        if(dayString.length()<2){
                            dayString="0"+dayString;
                        }
                        date=yearString+"-"+monthString+"-"+dayString;
                        arrayList=AllMatches.getMatchesFromGivenDate(date);
                        matchesAdapter.clear();
                        matchesAdapter.addAll(arrayList);
                        matchesAdapter.notifyDataSetChanged();
                        Log.d("DATE",date);
                    }
                }, year, monthOfYear, dayOfMonth);
                datePickerDialog.show();
            }
        });

    }
}
