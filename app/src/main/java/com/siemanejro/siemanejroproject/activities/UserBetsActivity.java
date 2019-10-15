package com.siemanejro.siemanejroproject.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.siemanejro.siemanejroproject.Adapters.BetsAdapter;
import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import communication.Client;
import model.Bet;

public class UserBetsActivity extends AppCompatActivity {

    //TODO: make choose button
   // Button chooseDateButton;
   // String selectedDate;
    BetsAdapter betsAdapter;
    ListView betsListView;
    ArrayList<Bet> listOfBets;

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
        setContentView(R.layout.activity_user_bets);

        setToolbarTitle(getString(R.string.userBetsActivityName));
        betsListView = findViewById(R.id.listOfUserBets);

        try {
            listOfBets = new LoadBet().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        setUserBets(listOfBets);

    }

    //TODO: add exception when there is no internet connection or server is down
    private void setUserBets(ArrayList<Bet> bets) {
        betsAdapter = new BetsAdapter(this, listOfBets);
        betsListView.setAdapter(betsAdapter);
    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title); // set title for toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true); //enable back press button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class LoadBet extends AsyncTask<Void, Void, ArrayList<Bet>> {

        @Override
        protected ArrayList<Bet> doInBackground(Void... voids) {
            Long loggedUserId = Client.SIEMAJERO.get().getLoggedInUser().getId();
            return (ArrayList<Bet>) Client.SIEMAJERO.get().getUsersBet(loggedUserId);
        }

        @Override
        protected void onPostExecute(ArrayList<Bet> bets) {
            super.onPostExecute(bets);
        }
    }

}
