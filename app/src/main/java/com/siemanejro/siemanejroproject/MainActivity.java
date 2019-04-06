package com.siemanejro.siemanejroproject;

import android.content.Intent;

import android.net.nsd.NsdManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addMatchButton;
    Button Register;
    Button typer;

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
                startActivity(new Intent(MainActivity.this, AddMatchActivity.class));
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
