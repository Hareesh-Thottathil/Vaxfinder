package com.example.vaxfinder.user.subActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Activity.home;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CovidDetailsActivity extends AppCompatActivity {

    FloatingActionButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_details);

        backBtn = findViewById(R.id.CDFloatBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CovidDetailsActivity.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}