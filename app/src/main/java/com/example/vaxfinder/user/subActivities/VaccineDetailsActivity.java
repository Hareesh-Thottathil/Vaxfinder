package com.example.vaxfinder.user.subActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Activity.home;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaccineDetailsActivity extends AppCompatActivity {

    FloatingActionButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);

        backbtn = findViewById(R.id.VDetailsfloatBtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VaccineDetailsActivity.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}