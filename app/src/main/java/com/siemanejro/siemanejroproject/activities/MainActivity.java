package com.siemanejro.siemanejroproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.siemanejro.siemanejroproject.R;

import com.siemanejro.siemanejroproject.utils.SharedPrefUtil;

public class MainActivity extends AppCompatActivity {

    Button Matches;
    Button GetUserBets;
    Button LogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Matches = findViewById(R.id.openMatches);
            GetUserBets = findViewById(R.id.getUserBetsbutton);
            LogoutButton = findViewById(R.id.logoutButton);

            addBettingButtonClicked();
            getUserBetsButtonClicked();
            logoutButtonClicked();
    }

    private void logoutButtonClicked() {
        LogoutButton.setOnClickListener(v -> {
            SharedPrefUtil.deleteLoggedUser(MainActivity.this);
            startActivity(new Intent(MainActivity.this, SplashScreenActivity.class));
        });
    }

    private void getUserBetsButtonClicked() {
        GetUserBets.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UserBetsActivity.class)));
    }

    private void addBettingButtonClicked() {
        Matches.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BettingActivity.class);
            startActivity(intent);
        });
    }

}
