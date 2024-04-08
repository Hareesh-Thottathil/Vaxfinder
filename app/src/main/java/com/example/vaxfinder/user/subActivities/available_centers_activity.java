package com.example.vaxfinder.user.subActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Activity.home;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class available_centers_activity extends AppCompatActivity {

    ListView centerListView;
    ArrayList<String> centers;
    FloatingActionButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_centers);

        SharedPreferences sharedPreferences = getSharedPreferences("Centers", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("centerList", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        centers = gson.fromJson(json, type);
        if (centers == null) {
            centers = new ArrayList<>();
        }

        centerListView = findViewById(R.id.centerListView);

        ArrayAdapter<String> centersAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,centers);
        centerListView.setAdapter(centersAdapter);

        backBtn = findViewById(R.id.FloatinBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(available_centers_activity.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}