package com.siemanejro.siemanejroproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonSyntaxException;
import com.siemanejro.siemanejroproject.typer.ChooseLeague;

public class MainActivity extends AppCompatActivity {

    Button addMatchButton;
    Button Register;
    Button typer;
    Button setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            addMatchButton = (Button) findViewById(R.id.openAddMatchActivity);
            Register = (Button) findViewById(R.id.openLoginActivity);
            typer = (Button) findViewById(R.id.openTyper);

            addMatchButtonClicked();
            addRegisterButtonClicked();
            addTyperButtonClicked();
        }
        catch (JsonSyntaxException e){

        }
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

    private void addRegisterButtonClicked() {
        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void addTyperButtonClicked() {
        typer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseLeague.class);
                startActivity(intent);
            }
        });
    }

}
