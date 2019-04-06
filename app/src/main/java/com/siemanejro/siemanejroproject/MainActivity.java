package com.siemanejro.siemanejroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addMatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMatchButton = (Button) findViewById(R.id.openAddMatchActivity);

        addMatchButtonClicked();
    }

    private void addMatchButtonClicked() {
        addMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMatchActivity.class));
            }
        });
    }
}
