package com.siemanejro.siemanejroproject.activities;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.R;
import com.siemanejro.siemanejroproject.adapters.BetDataAdapter;
import com.siemanejro.siemanejroproject.communication.Client;
import com.siemanejro.siemanejroproject.dataBinders.BetDataBinder;
import com.siemanejro.siemanejroproject.dataBinders.DataBinder;
import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.BetList;
import com.siemanejro.siemanejroproject.model.BetPageItem;
import com.siemanejro.siemanejroproject.model.League;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.RoomBet;
import com.siemanejro.siemanejroproject.utils.BetItemsUtil;
import com.siemanejro.siemanejroproject.utils.CalendarViewUtil;
import com.siemanejro.siemanejroproject.utils.MatchItemsUtil;
import com.siemanejro.siemanejroproject.utils.NetworkUtil;
import com.siemanejro.siemanejroproject.utils.SwipeDetector;
import com.siemanejro.siemanejroproject.utils.roomUtil.RoomService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarItemStyle;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarPredicate;

public class BettingActivity extends AppCompatActivity {

    /// Local variables ///

    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    Button saveButton;
    HorizontalCalendar horizontalCalendar;
    List<DataBinder> dataBinders;
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

        //get matches from API
        try {
            new LoadMatches().execute().get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        setUpCalendarView();

        //initialize recyclerView
        dataBinders = new ArrayList<>();
        rvBetsAdapter = new BetDataAdapter(dataBinders);
        rvBets.setAdapter(rvBetsAdapter);
        rvBets.setLayoutManager(linearLayoutManager);

        //TODO: make this as abstract class
        rvBets.setOnTouchListener(makeInstanceOfSwipeDetector());
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(this);
        allMatches = new ArrayList<>();
        betList = new BetList();
        dataBinders = new ArrayList<>();

        rvBets = findViewById(R.id.matchesList);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> savedUserBets());
        setToolbarTitleAndBackPressButton();
    }

    private void setToolbarTitleAndBackPressButton() {
        getSupportActionBar().setTitle("Matches"); // set title for toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true); //enable back press button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /// -------- CalendarView setup ----------

    private void setUpCalendarView() {
        CalendarViewUtil.setDatesWithMatches(allMatches);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_YEAR, -7);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_YEAR, 7);

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .disableDates(new HorizontalCalendarPredicate() {
                    @Override
                    public boolean test(Calendar date) {
                        return CalendarViewUtil.notContainsInDatesWithMatches(date);
                    }

                    @Override
                    public CalendarItemStyle style() {
                        return new CalendarItemStyle(Color.GRAY, null);
                    }
                })
                .build();

        setListenerToCalendar();
    }

    private void setListenerToCalendar() {
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                modifyListOfMatchesBySelectedDate(formatter.format(date.getTime()));
                CalendarViewUtil.setSelectedDate(date);
            }
        });
    }

    /// -------- Adapter methods -----------

    private void modifyListOfMatchesBySelectedDate(String selectedDate) {
        //clear bets in adapter
        rvBetsAdapter.notifyItemRangeRemoved(0, rvBetsAdapter.getItemCount());
        dataBinders.clear();
        dataBinders.addAll(
                BetItemsUtil.convertToDataBindersByDate(
                        MatchItemsUtil.filterByDate(allMatches, selectedDate),
                        selectedDate,
                        getApplicationContext()
                ));
        rvBetsAdapter.notifyItemRangeInserted(0, dataBinders.size());
    }

    /// -------- Methods for saving bets -----------

    private void savedUserBets() {
        List<Bet> bets = getNewUserBets();
        betList.clear();
        betList.addAll(bets);
        RoomService.insertBetsFromUI(RoomBet.transformToListFrom(bets), getApplicationContext());
        new PostBets().execute();
    }

    private List<Bet> getNewUserBets() {
        return dataBinders.stream().filter(dataBinder -> dataBinder.getItemViewType() == BetPageItem.TYPE_BET)
                .map(BetDataBinder.class::cast)
                .filter(betDataBinder -> betDataBinder.getBet().getChanged())
                .map(BetDataBinder::getBet)
                .collect(Collectors.toList());
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
                    modifyListOfMatchesBySelectedDate(formatter.format(Calendar.getInstance().getTime()));
                    break;
                }
            }
        }
    }

    //TODO: pass betList as parameter
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

    private SwipeDetector makeInstanceOfSwipeDetector() {
        return new SwipeDetector(getApplicationContext()) {
            @Override
            public void onLeftSwipe() {
                movementToTheRight();
            }

            @Override
            public void onRightSwipe() {
                movementToTheLeft();
            }
        };
    }

    private void movementToTheRight() {
        horizontalCalendar.selectDate(CalendarViewUtil.getNextClosestAvailableDate(), false);
    }

    private void movementToTheLeft() {
        horizontalCalendar.selectDate(CalendarViewUtil.getPreviousClosestAvailableDate(), false);
    }
}