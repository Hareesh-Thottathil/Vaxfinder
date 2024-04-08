package com.example.vaxfinder.admin.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaxfinder.R;
import com.example.vaxfinder.admin.Adapter.SlotAdapter;
import com.example.vaxfinder.admin.Domain.SlotList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class slots_activity extends AppCompatActivity {

    SlotAdapter adapter;
    Button addSlotBtn;
    RecyclerView slotsRecyclerView;

    ArrayList<SlotList> slotList;
    FloatingActionButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);

        SharedPreferences sharedPreferences = getSharedPreferences("MySlots", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("slotList", null);
        Type type = new TypeToken<ArrayList<SlotList>>() {}.getType();
        slotList = gson.fromJson(json, type);
        if (slotList == null) {
            slotList = new ArrayList<>();
        }

        slotsRecyclerView = findViewById(R.id.slotRecycleView);
        slotsRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        addSlotBtn = findViewById(R.id.addSlotBtn);

        addSlotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(slots_activity.this);
                dialog.setContentView(R.layout.dialogbox_add_slot);

                EditText txtVaccineName = dialog.findViewById(R.id.add_vName);
                EditText txtCenter = dialog.findViewById(R.id.add_center);
                EditText txtLoc = dialog.findViewById(R.id.add_loc);
                EditText txtDateTime = dialog.findViewById(R.id.add_dateAndTime);
                EditText txtAvailSlot = dialog.findViewById(R.id.add_AvailSlots);
                EditText txtBookingStatus = dialog.findViewById(R.id.add_BookingStatus);
                Button addBackBtn = dialog.findViewById(R.id.add_backBtn);
                Button addBtn= dialog.findViewById(R.id.add_Btn);
                dialog.show();

                addBtn.setOnClickListener(new View.OnClickListener() {
                    String vName="",center="",loc="",dateTime="",BookingStatus="", AvailableSlot;

                    @Override
                    public void onClick(View v) {
                        if(txtVaccineName.getText().toString().equals("")){
                            Toast.makeText(slots_activity.this, "Vaccine Name Field is Empty", Toast.LENGTH_SHORT).show();
                        }else if (txtCenter.getText().toString().equals("")) {
                            Toast.makeText(slots_activity.this, "Center Field is Empty", Toast.LENGTH_SHORT).show();
                        } else if (txtLoc.getText().toString().equals("")) {
                            Toast.makeText(slots_activity.this, "Location Field is Empty", Toast.LENGTH_SHORT).show();
                        }else if (txtDateTime.getText().toString().equals("")) {
                            Toast.makeText(slots_activity.this, "Date and Time Field is Empty", Toast.LENGTH_SHORT).show();
                        } else if (txtAvailSlot.getText().toString().equals("")) {
                            Toast.makeText(slots_activity.this, "Available Slots Field is Empty", Toast.LENGTH_SHORT).show();
                        } else if (txtBookingStatus.getText().toString().equals("")) {
                            Toast.makeText(slots_activity.this, "Booking Status Field is Empty", Toast.LENGTH_SHORT).show();
                        }else {
                            vName = txtVaccineName.getText().toString();
                            center = txtCenter.getText().toString();
                            loc = txtLoc.getText().toString();
                            dateTime = txtDateTime.getText().toString();
                            BookingStatus = txtBookingStatus.getText().toString();
                            AvailableSlot = txtAvailSlot.getText().toString();

                            slotList.add(new SlotList(dateTime,loc,vName,BookingStatus,center,AvailableSlot));
                            adapter.notifyItemInserted(slotList.size()-1);

                            dialog.dismiss();
                            Toast.makeText(slots_activity.this, "Registration Successfully" , Toast.LENGTH_SHORT).show();
                        }

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

        adapter = new SlotAdapter(this,slotList);
        slotsRecyclerView.setAdapter(adapter);

        backBtn = findViewById(R.id.FloatinBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(slots_activity.this, admin_home_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateToSharePreference() {
    }
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySlots", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(slotList);
        editor.putString("slotList", json);
        editor.apply();
    }
}