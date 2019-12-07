package com.siemanejro.siemanejroproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.siemanejro.siemanejroproject.R;

import com.siemanejro.siemanejroproject.communication.Client;
import com.siemanejro.siemanejroproject.utils.SharedPrefUtil;
import com.siemanejro.siemanejroproject.model.User;

public class SplashScreenActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.loadingTextView);

        Intent activityIntent;
        User user = SharedPrefUtil.getLoggerUser(SplashScreenActivity.this);
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
