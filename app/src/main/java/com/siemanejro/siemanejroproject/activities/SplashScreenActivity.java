package com.siemanejro.siemanejroproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.siemanejro.siemanejroproject.R;

import java.util.Optional;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent activityIntent;
        SharedPreferences myPrefs = this.getSharedPreferences(getString(R.string.login_preferences_file), SplashScreenActivity.MODE_PRIVATE);
        String userName = myPrefs.getString(getString(R.string.shPref_login_key), null);

        if(userName != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, LoginActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }
}
