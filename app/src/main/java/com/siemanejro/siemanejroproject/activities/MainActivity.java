package com.siemanejro.siemanejroproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonSyntaxException;
import com.siemanejro.siemanejroproject.ChooseLeague;
import com.siemanejro.siemanejroproject.R;

public class MainActivity extends AppCompatActivity {

    Button Matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Matches = findViewById(R.id.openTyper);

            addBettingButtonClicked();
        }
        catch (JsonSyntaxException e){

        }
    }

    private void addBettingButtonClicked() {
        Matches.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseLeague.class);
                startActivity(intent);
            }
        });
    }

}
