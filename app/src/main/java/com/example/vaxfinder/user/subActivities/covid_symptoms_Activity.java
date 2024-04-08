package com.example.vaxfinder.user.subActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Activity.home;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class covid_symptoms_Activity extends AppCompatActivity {

    FloatingActionButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_symptoms);

        backbtn = findViewById(R.id.CSFloatinBtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(covid_symptoms_Activity.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}