package com.siemanejro.siemanejroproject.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.Adapters.BetsAdapter;
import com.siemanejro.siemanejroproject.Adapters.RVBetsAdapter;
import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import communication.Client;
import model.Bet;
import model.Score;
import utils.NetworkUtil;
import utils.ResultUtil;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class UserBetsActivity extends AppCompatActivity {

    //TODO: make choose button
   // Button chooseDateButton;
   // String selectedDate;
    RVBetsAdapter rvBetsAdapter;
    RecyclerView rvBets;
    ArrayList<Bet> listOfBets = null;
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
        setContentView(R.layout.activity_user_bets);

        setToolbarTitle(getString(R.string.userBetsActivityName));
        rvBets = findViewById(R.id.rvUserBets);

        try {
            new getBets().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // TODO: show lastly upload user's bets
    private void putUserBetsToAdapter() {
        checkoutBets();
//        betsAdapter = new BetsAdapter(this, listOfBets);
//        betsListView.setAdapter(betsAdapter);
        rvBetsAdapter = new RVBetsAdapter(listOfBets);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), HORIZONTAL);
        rvBets.addItemDecoration(itemDecor);
        rvBets.setAdapter(rvBetsAdapter);
        rvBets.setLayoutManager(linearLayoutManager);
    }

    private void checkoutBets() {
        for(Bet b: listOfBets) {
            Score matchScore = b.getMatch().getScore();
            if(matchScore.getWinner() != null) {
                b.setResult(ResultUtil.calculateResult(matchScore, b.getUserScore()));
            }
        }
    }

    private void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title); // set title for toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true); //enable back press button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class getBets extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            if(!NetworkUtil.isNetworkConnectionAvailable((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
                return 0;
            }
            listOfBets = (ArrayList<Bet>) Client.SIEMAJERO.get().getLoggedInUserBets();
            if(listOfBets == null) {
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
                    Toast toast = Toast.makeText(UserBetsActivity.this,"No internet connection", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
                case 1 : {
                    Toast toast = Toast.makeText(UserBetsActivity.this,"Something went wrong", Toast.LENGTH_LONG);
                    toast.show();
                    break;
                }
                case 2 : {
                    putUserBetsToAdapter();
                    break;
                }

            }
        }
    }

}
