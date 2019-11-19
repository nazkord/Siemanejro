package com.siemanejro.siemanejroproject.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
import android.widget.EditText;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.adapters.RVMatchesAdapter;
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
import model.FullTimeResult;
import model.Match;
import model.Score;
import utils.NetworkUtil;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class BettingActivity extends AppCompatActivity {

    /// Local variables ///

    Button saveButton;
    Button chooseDateButton;
    Long leagueID;
    String leagueName;
    String selectedDate;

    RVMatchesAdapter rvBetsAdapter;
    RecyclerView rvBets;
    List<Match> allMatches = null;
    List<Bet> betsInRV = new ArrayList<>();
    BetList betList = new BetList();
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


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
            new LoadMatches().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        rvBetsAdapter = new RVMatchesAdapter();

        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), HORIZONTAL);
        rvBets.addItemDecoration(itemDecor);
        rvBets.setAdapter(rvBetsAdapter);
        rvBets.setLayoutManager(linearLayoutManager);
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


    /// -------- RecyclerView and Adapter methods -----------


    private ArrayList<Bet> expandMatchesToBets(List<Match> matches) {
        return (ArrayList<Bet>) matches.stream()
                .map(m -> new Bet(null, m, null, new Score(null, null, new FullTimeResult(null, null, null)), null))
                .collect(Collectors.toList());
    }

    public List<Match> getMatchesFromSelectedDate(String date) {
        return allMatches.stream()
                .filter(Match -> Match.getUtcDate().substring(0,10).equals(date))
                .collect(Collectors.toList());
    }

    private void modifyListOfMatchesByDate(String dateInString) {
        //clear bets in adapter
        rvBetsAdapter.notifyItemRangeRemoved(0, rvBetsAdapter.getItemCount());
        betsInRV.clear();
        betsInRV.addAll(expandMatchesToBets(getMatchesFromSelectedDate(dateInString)));

        //TODO: is it a good approach?
        rvBetsAdapter.setBets((ArrayList<Bet>) betsInRV);
        //notify of new bets inserted
        rvBetsAdapter.notifyItemRangeInserted(0, betsInRV.size());
    }

    /// -------- Methods for saving bets -----------

    private void savedUserBets() {
        List<Bet> bets = getNewUserBets();
        betList.clear();
        betList.addAll(bets);
        new PostBets().execute();
    }

    private List<Bet> getNewUserBets() {
        View betView;
        Bet betItem;
        EditText userBet1;
        EditText userBet2;

        int numberOfMatches = rvBetsAdapter.getItemCount();
        List<Bet> bets = new ArrayList<>();

        for (int i = 0; i < numberOfMatches; i++)
        {
            betItem = rvBetsAdapter.getItem(i);
            linearLayoutManager.scrollToPosition(i);
            betView = linearLayoutManager.findViewByPosition(i);

//            betView = rvBets.getChildAt(i);

            //TODO: IMPORTANT: doesn't work every time
            userBet1 = betView.findViewById(R.id.result1);
            userBet2 = betView.findViewById(R.id.result2);
            if(userBet1.getText().toString().isEmpty() || userBet2.getText().toString().isEmpty())
                continue;
            Integer userBetResult1 = Integer.parseInt(userBet1.getText().toString());
            Integer userBetResult2 = Integer.parseInt(userBet2.getText().toString());

            FullTimeResult fullTimeResult = new FullTimeResult(null, userBetResult1, userBetResult2);

            Score userScore = new Score(null, null, fullTimeResult);
            userScore.setWinner(userScore.getWinnerForScore());

            betItem.setUserScore(userScore);

            bets.add(betItem);
        }
        return bets;
    }

    /// -------- onClicker's and DatePickerDialog -----------

    private void saveButtonClicked() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedUserBets();
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

    /// -------- Background Threads -----------

    private class LoadMatches extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            if(!NetworkUtil.isNetworkConnectionAvailable((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
                return 0;
            }
            allMatches = Client.SIEMAJERO.get().getMatchesByCompetition(leagueID);
            if(allMatches == null) {
                //TODO: it could be an error by server side or there are just no matches at all
                return 1;
            } else {
                return 2;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case 0 : {
                    Toast toast = Toast.makeText(BettingActivity.this,"No internet connection", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
                case 1 : {
                    Toast toast = Toast.makeText(BettingActivity.this,"Something went wrong (server side)", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
                case 2: {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    selectedDate = LocalDateTime.now().format(dateFormat);
                    modifyListOfMatchesByDate(selectedDate);
                    break;
                }
            }
        }
    }

    private class PostBets extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            if(!NetworkUtil.isNetworkConnectionAvailable((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
                return 0;
            }
            if(Client.SIEMAJERO.get().postUsersBet(betList)) {
                return 1;
            } else {
                return 2;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case 0: {
                    Toast toast = Toast.makeText(BettingActivity.this,"No internet connection", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
                case 1: {
                    Toast toast = Toast.makeText(BettingActivity.this,"Data Saved", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
                case 2: {
                    Toast toast = Toast.makeText(BettingActivity.this,"Something went wrong", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
            }

        }
    }
}
