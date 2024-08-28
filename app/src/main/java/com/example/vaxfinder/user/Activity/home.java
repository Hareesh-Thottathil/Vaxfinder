package com.example.vaxfinder.user.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Adapter.PersonAdapter;
import com.example.vaxfinder.user.Domain.PersonList;
import com.example.vaxfinder.user.subActivities.CovidDetailsActivity;
import com.example.vaxfinder.user.subActivities.VaccineDetailsActivity;
import com.example.vaxfinder.user.subActivities.available_centers_activity;
import com.example.vaxfinder.user.subActivities.available_slots_activity;
import com.example.vaxfinder.user.subActivities.covid_symptoms_Activity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class home extends AppCompatActivity {


    CardView symptoms, covidDetail, avaiSlots, vdetails, availCenters;
    PersonAdapter adapter;
    Button addMemberBtn, addRegisterBtn, addBackBtn;
    private RecyclerView personRecyclerView;
    ArrayList<PersonList> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("personList", null);
        Type type = new TypeToken<ArrayList<PersonList>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        personRecyclerView = findViewById(R.id.personRecyclerView);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addMemberBtn = findViewById(R.id.addPersonBtn);

        addMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(home.this);
                dialog.setContentView(R.layout.activity_add_person);

                EditText txtname = dialog.findViewById(R.id.add_name);
                addBackBtn = dialog.findViewById(R.id.add_backBtn);
                addRegisterBtn = dialog.findViewById(R.id.add_registerBtn);
                dialog.show();

                addRegisterBtn.setOnClickListener(new View.OnClickListener() {
                    String Name = "";

                    @Override
                    public void onClick(View v) {
                        if(arrayList.size()<6) {
                            if (txtname.getText().toString().equals("")) {
                                Toast.makeText(home.this, "Name Field Cannot Be Empty", Toast.LENGTH_SHORT).show();
                            } else {
                                Name = txtname.getText().toString();
                                Toast.makeText(home.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                            }
                            arrayList.add(new PersonList(Name,R.drawable.man_icon_1));
                            adapter.notifyItemInserted(arrayList.size() - 1);
                        }else {
                            Toast.makeText(home.this, "limit reached. Cannot add more people at this account", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                addBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        symptoms = findViewById(R.id.covidSymptomsCard);
        covidDetail = findViewById(R.id.covidDetailsCard);
        vdetails = findViewById(R.id.vaccineDetailsCard);
        avaiSlots = findViewById(R.id.covidSlotCard);
        availCenters = findViewById(R.id.centerDetailsCard);

        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, covid_symptoms_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        covidDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, CovidDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, VaccineDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        avaiSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, available_slots_activity.class);
                startActivity(intent);
                finish();
            }
        });

        availCenters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, available_centers_activity.class);
                startActivity(intent);
                finish();
            }
        });


        adapter = new PersonAdapter(this, arrayList);
        personRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("personList", json);
        editor.apply();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
        builder.setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(home.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
                        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("user_logdata",false);
                        editor.apply();
                        Intent intent = new Intent(home.this, com.example.vaxfinder.MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}