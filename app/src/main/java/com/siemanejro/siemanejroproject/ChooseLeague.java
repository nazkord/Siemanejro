package com.siemanejro.siemanejroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLeague extends AppCompatActivity {

    Button germanButton;
    Button spainButton;
    Button franceButton;
    Button italyButton;
    Button englandButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_league);

        germanButton = (Button)findViewById(R.id.germanButton);
        spainButton = (Button)findViewById(R.id.spainButton);
        franceButton = (Button)findViewById(R.id.franceButton);
        italyButton = (Button)findViewById(R.id.italyButton);
        englandButton = (Button)findViewById(R.id.englandButton);

        germanButtonClicked();
        spainButtonClicked();
        franceButtonClicked();
        italyButtonClicked();
        englandButtonClicked();


    }

    private void germanButtonClicked (){
        germanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ChooseLeague.this, Tipp.class);
                intent.putExtra("leagueID","2002");
                intent.putExtra("leagueName","Germany League");
                startActivity(intent);
            }
        });
    }

    private void spainButtonClicked () {
        spainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLeague.this, Tipp.class);
                intent.putExtra("leagueID", "2014");
                intent.putExtra("leagueName","Spain League");
                startActivity(intent);
            }
        });
    }
    private void franceButtonClicked (){
            franceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(ChooseLeague.this, Tipp.class);
                    intent.putExtra("leagueID","2015");
                    intent.putExtra("leagueName","France League");
                    startActivity(intent);
                }
            });
        }

    private void italyButtonClicked (){
        italyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ChooseLeague.this, Tipp.class);
                intent.putExtra("leagueID","2019");
                intent.putExtra("leagueName","Italy League");
                startActivity(intent);
            }
        });
    }

    private void englandButtonClicked (){
        englandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ChooseLeague.this, Tipp.class);
                intent.putExtra("leagueID","2021");
                intent.putExtra("leagueName","England League");
                startActivity(intent);
            }
        });
    }
    }

