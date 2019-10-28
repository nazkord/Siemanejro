package com.siemanejro.siemanejroproject.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.Adapters.MatchesAdapter2;
import com.siemanejro.siemanejroproject.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import communication.Client;
import model.Bet;
import model.BetList;
import model.Match;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class BettingActivity extends AppCompatActivity {

    /// Local variables ///

    Button saveButton;
    Button chooseDateButton;
    MatchesAdapter2 matchesAdapter2;
    Long leagueID;
    String leagueName;
    String selectedDate;

    RecyclerView rvBets;
    List<Match> allMatches;
    List<Bet> betsInRV = new ArrayList<>();
    BetList betList = new BetList();

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
        setContentView(R.layout.activity_betting);

        init();

        //get matches from API
        try {
            allMatches = new LoadMatches().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        selectedDate = LocalDateTime.now().format(dateFormat);
        betsInRV = expandMatchesToBets(getMatchesFromSelectedDate(selectedDate));

        // Create adapter passing in bets with chosen matches
        matchesAdapter2 = new MatchesAdapter2((ArrayList<Bet>) betsInRV);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), HORIZONTAL);
        rvBets.addItemDecoration(itemDecor);
        rvBets.setAdapter(matchesAdapter2);
        rvBets.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {
        rvBets = findViewById(R.id.matchesList);
        saveButton = (Button) findViewById(R.id.saveButton);
        chooseDateButton = findViewById(R.id.choose_date_button);
        Intent intent = getIntent();
        leagueID = intent.getLongExtra("leagueID", 0);
        leagueName = intent.getStringExtra("leagueName");
        setToolbarTitleAndBackPressButton(leagueName);
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
//                savedUserBets();
                Toast toast = Toast.makeText(BettingActivity.this,"Data Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private ArrayList<Bet> expandMatchesToBets(List<Match> matches) {
        return (ArrayList<Bet>) matches.stream()
                .map(m -> new Bet(null, m, null, null, null))
                .collect(Collectors.toList());
    }

//    private void savedUserBets() {
//        List<Bet> bets = getNewUserBets();
//        betList.addAll(bets);
//        new PostBets().execute();
//    }

//    private List<Bet> getNewUserBets() {
//        View matchView;
//        EditText userBet1;
//        EditText userBet2;
//
//        int numberOfMatches = matchesListView.getChildCount();
//        List<Bet> bets = new ArrayList<>();
//
//        for (int i = 0; i < numberOfMatches; i++)
//        {
//            matchView = matchesListView.getChildAt(i);
//
//
//            userBet1 = (EditText) matchView.findViewById(R.id.result1);
//            userBet2 = (EditText) matchView.findViewById(R.id.result2);
//            Integer userBetResult1 = Integer.parseInt(userBet1.getText().toString());
//            Integer userBetResult2 = Integer.parseInt(userBet2.getText().toString());
//
//            Score userScore = new Score(null, getWinnerForScore(userBetResult1,userBetResult2),
//                    new FullTimeResult(null, userBetResult1, userBetResult2));
//
//            //TODO: IMPORTANT: result should be automatically counted by computing class
//            bets.add(new Bet(null, matchesInsideLV.get(i), null, userScore, 0));
//        }
//        return bets;
//    }

    private String getWinnerForScore(Integer a, Integer b) {
        if(a > b) {
            return "HOME_TEAM";
        } else if (b > a) {
            return "AWAY_TEAM";
        } else {
            return "DRAW";
        }
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
        int year = Integer.parseInt(selectedDate.substring(0, 4));
        int monthOfYear = Integer.valueOf(selectedDate.substring(5,7)) - 1;
        int dayOfMonth = Integer.parseInt(selectedDate.substring(8, 10));

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
        //clear bets in adapter
        matchesAdapter2.notifyItemRangeRemoved(0, matchesAdapter2.getItemCount());
        betsInRV.clear();
        betsInRV.addAll(expandMatchesToBets(getMatchesFromSelectedDate(dateInString)));
        //notify of new bets inserted
        matchesAdapter2.notifyItemRangeInserted(0, betsInRV.size());
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

    private class PostBets extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Client.SIEMAJERO.get().postUsersBet(betList);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //TODO: show error message (???) or success ?;
        }
    }
}
