package com.siemanejro.siemanejroproject;

import android.content.Intent;

import android.net.nsd.NsdManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Match;

public class MainActivity extends AppCompatActivity {

    Button addMatchButton;
    Button Register;
    Button typer;
    ArrayList<Match> listOfMatches=new ArrayList<>();

    public static final String MESSAGE_LIST_OF_MATCHES="list of matches";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMatchButton = (Button) findViewById(R.id.openAddMatchActivity);
        Register = (Button) findViewById(R.id.openLoginActivity);
        typer = (Button) findViewById(R.id.openTyper);

        addMatchButtonClicked();
        addRegisterButtonClicked();
        addTyperButtonClicked();
    }



    private void addMatchButtonClicked() {
        addMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, AddMatchActivity.class);
                Log.d("AAAAAAAAAA",listOfMatches.toString());
                intent.putExtra(MESSAGE_LIST_OF_MATCHES,listOfMatches);
                startActivity(intent);
            }
        });
    }

    private void addRegisterButtonClicked(){
        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void addTyperButtonClicked(){
        typer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tipp.class));
            }
        });
    }
}
