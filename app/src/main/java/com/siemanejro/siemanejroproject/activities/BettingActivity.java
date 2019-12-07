package com.siemanejro.siemanejroproject.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.R;
import com.siemanejro.siemanejroproject.adapters.BetDataAdapter;
import com.siemanejro.siemanejroproject.dataBinders.DataBinder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.siemanejro.siemanejroproject.communication.Client;
import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.BetList;
import com.siemanejro.siemanejroproject.model.FullTimeResult;
import com.siemanejro.siemanejroproject.model.League;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.Score;
import com.siemanejro.siemanejroproject.utils.BetItemsUtil;
import com.siemanejro.siemanejroproject.utils.NetworkUtil;

public class BettingActivity extends AppCompatActivity {

    /// Local variables ///

    Button saveButton;
    Button chooseDateButton;
    String selectedDate;

    List<DataBinder> dataBinders = new ArrayList<>();
    BetDataAdapter rvBetsAdapter;
    RecyclerView rvBets;
    List<Match> allMatches;
    BetList betList;
    LinearLayoutManager linearLayoutManager;

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

//        get matches from API
        try {
            new LoadMatches().execute().get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

//        new CommunicationAsync<Long, ArrayList<Match>>(id -> (ArrayList<Match>) Client.SIEMAJERO.get().getMatchesByCompetition(leagueID))
//                .onSuccess(this::displaySuccess)
//                .onError(this::displayError)
//                .execute(leagueID);

        rvBetsAdapter = new BetDataAdapter(dataBinders); //betInRv
        rvBets.setAdapter(rvBetsAdapter);
        rvBets.setLayoutManager(linearLayoutManager);

//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        selectedDate = LocalDateTime.now().format(dateFormat);
//        modifyListOfMatchesByDate(selectedDate);
    }

//    private void displayError(Exception e) {
//        System.out.println(e.getMessage());
//        e.printStackTrace();
//    }
//
//    private void displaySuccess(ArrayList<Match> matches) {
//        dataBinders = BetItemsUtil.convertToDataBinders(expandMatchesToBets(matches));
//    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(this);
        allMatches = new ArrayList<>();
        betList = new BetList();

        rvBets = findViewById(R.id.matchesList);
        saveButton = findViewById(R.id.saveButton);
        chooseDateButton = findViewById(R.id.choose_date_button);
        setToolbarTitleAndBackPressButton("Matches");
        saveButtonClicked();
        chooseDateClicked();
    }

    private void setToolbarTitleAndBackPressButton(String title) {
        getSupportActionBar().setTitle(title); // set title for toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true); //enable back press button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /// -------- Adapter methods -----------


    private ArrayList<Bet> expandMatchesToBets(List<Match> matches) {
        return (ArrayList<Bet>) matches.stream()
                .map(match-> new Bet(null, match, null, new Score(null, null, new FullTimeResult(null, null, null)), null))
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
        dataBinders.clear();
        dataBinders.addAll(BetItemsUtil.convertToDataBinders(expandMatchesToBets(getMatchesFromSelectedDate(dateInString))));
        rvBetsAdapter.notifyItemRangeInserted(0, dataBinders.size());
    }

    /// -------- Methods for saving bets -----------

    private void savedUserBets() {
        List<Bet> newUserBets = getNewUserBets();
        betList.clear();
        betList.addAll(newUserBets);
        new PostBets().execute();
    }

    private List<Bet> getNewUserBets() {
//        BetItem betItem;
//        Bet bet;
//
//        int numberOfMatches = rvBetsAdapter.getItemCount();
//        List<Bet> bets = new ArrayList<>();

//        for (int i = 0; i < numberOfMatches; i++)
//        {
//            betItem = (BetItem) rvBetsAdapter.getItem(i);
//            bet = betItem.getBet();
//
//            Integer userBetResult1 = bet.getUserScore().getFullTime().getHomeTeam();
//            Integer userBetResult2 = bet.getUserScore().getFullTime().getAwayTeam();
//
//            if(userBetResult1 == null || userBetResult2 == null)
//                continue;
//
//            Score userScore = new Score(null, null,
//                new FullTimeResult(null, userBetResult1, userBetResult2));
//            userScore.setWinner(userScore.getWinnerForScore());
//            bet.setUserScore(userScore);
//
//            bets.add(bet);
//        }
        return null;
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

            //TODO: can I write [League.values()::getLeagueId()]
            List<Long> leagueIds = new ArrayList<>();
            Stream.of(League.values())
                    .forEach(league -> leagueIds.add(league.getLeagueId()));

            allMatches = Client.SIEMAJERO.get().getMatchesByCompetitions(leagueIds);

            if(allMatches == null) {
                //TODO: it could be an error by server side or there are just no matches at all
                return 1;
            } else {
                dataBinders = BetItemsUtil.convertToDataBinders(expandMatchesToBets(allMatches));
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
