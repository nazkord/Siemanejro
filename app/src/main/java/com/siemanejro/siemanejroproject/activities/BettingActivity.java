package com.siemanejro.siemanejroproject.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.Adapters.MatchesAdapter;
import com.siemanejro.siemanejroproject.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import communication.Client;
import model.Match;

public class BettingActivity extends AppCompatActivity {

    /// Local variables ///

    Button saveButton;
    Button chooseDateButton;
    MatchesAdapter matchesAdapter;
    ListView listView;
    Long leagueID;
    String leagueName;
    String selectedDate;
    List<Match> allMatches;

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

        init();

        Intent intent = getIntent();
        leagueID = intent.getLongExtra("leagueID", 0);
        leagueName = intent.getStringExtra("leagueName");

        setToolbarTitleAndBackPressButton(leagueName);

        try {
            allMatches = new LoadMatches().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        //TODO: code to replace (request with my own API)

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        selectedDate = LocalDateTime.now().format(dateFormat);
        matchesAdapter = new MatchesAdapter(this, (ArrayList<Match>) getMatchesFromSelectedDate(selectedDate));
        listView.setAdapter(matchesAdapter);
    }

    private void init() {
        listView = (ListView)findViewById(R.id.matches_list);
        listView = (ListView) findViewById(R.id.matches_list);
        saveButton = (Button) findViewById(R.id.saveButton);
        chooseDateButton = findViewById(R.id.choose_date_button);
        saveButtonClicked();
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
                Toast toast = Toast.makeText(BettingActivity.this,"Data Saved", Toast.LENGTH_SHORT);
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
        matchesAdapter.clear();
        matchesAdapter.addAll(getMatchesFromSelectedDate(dateInString));
        matchesAdapter.notifyDataSetChanged();
    }

    public List<Match> getMatchesFromSelectedDate(String date) {
        return allMatches.stream()
                .filter(Match -> Match.getUtcDate().substring(0,10).equals(date))
                .collect(Collectors.toList());
    }

    private class LoadMatches extends AsyncTask<Void, Void, ArrayList<Match>> {

        @Override
        protected ArrayList<Match> doInBackground(Void... voids) {
            return (ArrayList<Match>) Client.SIEMAJERO.get().getMatchesByCompetition(leagueID);
        }

        @Override
        protected void onPostExecute(ArrayList<Match> matches) {
            super.onPostExecute(matches);
        }
    }
}
