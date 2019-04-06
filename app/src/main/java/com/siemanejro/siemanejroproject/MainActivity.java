package com.siemanejro.siemanejroproject;

import android.content.Intent;

import android.net.nsd.NsdManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.JsonSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import json.JsonImport;
import model.AllMatches;
import model.Match;

public class MainActivity extends AppCompatActivity {

    Button addMatchButton;
    Button Register;
    Button typer;
    Button setting;
    ArrayList <Match> listOfMatches = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            AllMatches allMatches = JsonImport.importMatchesFPM("2021");
            //allMatches.update();
           // Log.d("AAAAAAAAAA",allMatches.getMatches().toString());

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            addMatchButton = (Button) findViewById(R.id.openAddMatchActivity);
            Register = (Button) findViewById(R.id.openLoginActivity);
            typer = (Button) findViewById(R.id.openTyper);

            addMatchButtonClicked();
            addRegisterButtonClicked();
            addTyperButtonClicked();
        }
        catch (JsonSyntaxException e){}
    }


    private void addMatchButtonClicked() {
        addMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, AddMatchActivity.class);
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
