package com.siemanejro.siemanejroproject.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.siemanejro.siemanejroproject.Adapters.BetsAdapter;
import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import communication.Client;
import model.Bet;

public class UserBetsActivity extends AppCompatActivity {

    //TODO: make choose button or
   // Button chooseDateButton;
   // String selectedDate;
    BetsAdapter betsAdapter;
    ListView betsListView;
    ArrayList<Bet> listOfBets;

    public void setListOfBets(ArrayList<Bet> listOfBets) {
        this.listOfBets = listOfBets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bets);

        setToolbarTitle(getString(R.string.userBetsActivityName));

        //TODO: I should make this connection via AsyncTask, but i don't want to make AsyncTask in every activity through
        //TODO: which i connect to my server. How to resolve this problem???

        betsListView = findViewById(R.id.listOfUserBets);
//        Long loggedUserId = Client.SIEMAJERO.get().getLoggedInUser().getId();
//        listOfBets = (ArrayList<Bet>) Client.SIEMAJERO.get().getUsersBet(loggedUserId);

        try {
            listOfBets = new LoadBet().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        betsAdapter = new BetsAdapter(this, listOfBets);
        betsListView.setAdapter(betsAdapter);
    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title); // set title for toolbar
    }

    private class LoadBet extends AsyncTask<Void, Void, ArrayList<Bet>> {

        private ArrayList<Bet> userBets;

        @Override
        protected ArrayList<Bet> doInBackground(Void... voids) {
            Long loggedUserId = Client.SIEMAJERO.get().getLoggedInUser().getId();
            userBets = (ArrayList<Bet>) Client.SIEMAJERO.get().getUsersBet(loggedUserId);
            return userBets;
        }

//        @Override
//        protected void onPostExecute(ArrayList<Bet> bets) {
//            super.onPostExecute(bets);
//            //setListOfBets(userBets);
//        }
    }

}
