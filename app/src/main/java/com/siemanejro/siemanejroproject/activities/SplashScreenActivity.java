package com.siemanejro.siemanejroproject.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.siemanejro.siemanejroproject.R;

import com.siemanejro.siemanejroproject.communication.Client;
import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.RoomBet;
import com.siemanejro.siemanejroproject.utils.NetworkUtil;
import com.siemanejro.siemanejroproject.utils.SharedPrefUtil;
import com.siemanejro.siemanejroproject.model.User;
import com.siemanejro.siemanejroproject.utils.roomUtil.RoomService;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.loadingTextView);

        Intent activityIntent;
        User user = new SharedPrefUtil(this).getLoggerUser();
        Client.SIEMAJERO.get().setLoggedInUser(user);

        if(user.getName() != null) {
            activityIntent = new Intent(this, MainActivity.class);
            new LoadBet().execute();
        } else {
            activityIntent = new Intent(this, LoginActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }

    private class LoadBet extends AsyncTask<Void, Void, List<Bet>> {

        @Override
        protected List<Bet> doInBackground(Void... voids) {
            if(!NetworkUtil.isNetworkConnectionAvailable((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))) {
                return null;
            }
            return Client.SIEMAJERO.get().getLoggedInUserBets();
        }

        @Override
        protected void onPostExecute(List<Bet> bets) {
            super.onPostExecute(bets);
            if(bets == null) {
                Toast toast = Toast.makeText(SplashScreenActivity.this,"No internet connection", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            List<RoomBet> betsForDB = RoomBet.transformToListFrom(bets);
            RoomService.insertBetsFromServer(betsForDB, getApplicationContext());
        }
    }
}
