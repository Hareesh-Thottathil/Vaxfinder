package com.example.vaxfinder.admin.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vaxfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class center_activity extends AppCompatActivity {

    Button addCenterBtn;
    ListView centerListView;

    ArrayList<String> centers;
    FloatingActionButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        SharedPreferences sharedPreferences = getSharedPreferences("Centers", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("centerList", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        centers = gson.fromJson(json, type);
        if (centers == null) {
            centers = new ArrayList<>();
        }

        addCenterBtn = findViewById(R.id.addCenterBtn);
        centerListView = findViewById(R.id.centerListView);
        
        ArrayAdapter<String> centersAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,centers);

        centerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(center_activity.this);
                builder.setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                centers.remove(position);
                                centersAdapter.notifyDataSetChanged();
                                saveCentersToSharedPreferences();
                                Toast.makeText(center_activity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
        addCenterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(center_activity.this);
                dialog.setContentView(R.layout.center_dialogbox);

                EditText txtCenterName = dialog.findViewById(R.id.add_centerName);
                EditText txtCenterLoc = dialog.findViewById(R.id.add_loc);
                Button addBackBtn = dialog.findViewById(R.id.add_backBtn);
                Button addBtn= dialog.findViewById(R.id.add_Btn);
                dialog.show();

                addBtn.setOnClickListener(new View.OnClickListener() {
                    String cName="",loc="";

                    @Override
                    public void onClick(View v) {
                        if(txtCenterName.getText().toString().equals("")){
                            Toast.makeText(center_activity.this, "Center Name Field is Empty", Toast.LENGTH_SHORT).show();
                        } else if (txtCenterLoc.getText().toString().equals("")) {
                            Toast.makeText(center_activity.this, "Center Location Field is Empty", Toast.LENGTH_SHORT).show();
                        }else {
                            loc = txtCenterLoc.getText().toString();
                            cName = txtCenterName.getText().toString() + "," + loc;
                            Toast.makeText(center_activity.this, "Center Added Successfully" , Toast.LENGTH_SHORT).show();

                            centers.add(cName);
                            centersAdapter.notifyDataSetChanged();
                            dialog.dismiss();
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
        centerListView.setAdapter(centersAdapter);

        backBtn = findViewById(R.id.FloatinBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(center_activity.this, admin_home_activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void saveCentersToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Centers",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(centers);
        editor.putString("centerList",json);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("Centers", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(centers);
        editor.putString("centerList", json);
        editor.apply();
    }
}