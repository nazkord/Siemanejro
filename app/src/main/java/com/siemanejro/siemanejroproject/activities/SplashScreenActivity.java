package com.siemanejro.siemanejroproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import communication.Client;
import utils.SharedPrefUtil;
import model.User;

public class SplashScreenActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.loadingTextView);

        Intent activityIntent;
        User user = SharedPrefUtil.LOGIN_SHARED_PREF_UTIL.getLoggerUser(SplashScreenActivity.this);
        Client.SIEMAJERO.get().setLoggedInUser(user);

        if(user.getName() != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, LoginActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }
}
