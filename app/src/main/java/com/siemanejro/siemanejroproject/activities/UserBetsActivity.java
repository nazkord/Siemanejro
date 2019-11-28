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
import android.widget.Toast;

import com.siemanejro.siemanejroproject.adapters.RVBetsAdapter;
import com.siemanejro.siemanejroproject.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import communication.Client;
import communication.CommunicationAsync;
import model.Bet;
import model.Score;
import utils.NetworkUtil;
import utils.ResultUtil;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;

public class UserBetsActivity extends AppCompatActivity {

    RVBetsAdapter rvBetsAdapter;
    RecyclerView rvBets;
    ArrayList<Bet> listOfBets = new ArrayList<>();
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

        putUserBetsToAdapter();

        try {
            new CommunicationAsync<Void, ArrayList<Bet>>( b -> (ArrayList<Bet>) Client.SIEMAJERO.get().getLoggedInUserBets())
                    .onSuccess(this::displaySuccess)
                    .onError(this::displayError)
                    .execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void displaySuccess(ArrayList<Bet> bets) {
        Toast toast = Toast.makeText(UserBetsActivity.this,"OK", Toast.LENGTH_LONG);
        toast.show();
        listOfBets = bets;
    }

    private void displayError(Exception e) {
        e.printStackTrace();
        Toast toast = Toast.makeText(UserBetsActivity.this, "BAD", Toast.LENGTH_LONG);
        toast.show();
    }

    // TODO: show lastly upload user's bets
    private void putUserBetsToAdapter() {
//        checkoutBets();
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
                //TODO: user can has none of bets
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
