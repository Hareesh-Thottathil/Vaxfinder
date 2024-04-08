package com.example.vaxfinder.user.subActivities;

import static com.example.vaxfinder.R.id.availfloatBtn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.vaxfinder.R;
import com.example.vaxfinder.admin.Domain.SlotList;
import com.example.vaxfinder.user.Activity.home;
import com.example.vaxfinder.user.Adapter.AvailableSlotAdapter;
import com.example.vaxfinder.user.Domain.PersonList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class available_slots_activity extends AppCompatActivity {

    AvailableSlotAdapter adapter;

    RecyclerView userAvailableSlotRecyclerView;

    ArrayList<SlotList> slotList;
    FloatingActionButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_slots);

        SharedPreferences sharedPreferences = getSharedPreferences("MySlots", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("slotList", null);
        Type type = new TypeToken<ArrayList<SlotList>>() {}.getType();
        slotList = gson.fromJson(json, type);
        if (slotList == null) {
            slotList = new ArrayList<>();
        }

        userAvailableSlotRecyclerView = findViewById(R.id.userAvailableSlotRecyclerView);
        userAvailableSlotRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter = new AvailableSlotAdapter(this,slotList);
        userAvailableSlotRecyclerView.setAdapter(adapter);

        backBtn = findViewById(R.id.availfloatBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(available_slots_activity.this, home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}