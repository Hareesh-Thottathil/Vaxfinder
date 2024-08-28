package com.example.vaxfinder.admin.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.vaxfinder.R;

public class admin_home_activity extends AppCompatActivity {

    CardView postSlot, fechRec, userMng, centerMng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        postSlot = findViewById(R.id.PostSlotCard);
        fechRec = findViewById(R.id.fechRecCard);
        userMng = findViewById(R.id.userManageCard);
        centerMng = findViewById(R.id.centerCard);

        postSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_home_activity.this, slots_activity.class);
                startActivity(intent);
                finish();
            }
        });

        fechRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(admin_home_activity.this, "Database Not Set", Toast.LENGTH_SHORT).show();
            }
        });

        userMng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(admin_home_activity.this, "Database Not Set", Toast.LENGTH_SHORT).show();
            }
        });

        centerMng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_home_activity.this, center_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
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
        AlertDialog.Builder builder = new AlertDialog.Builder(admin_home_activity.this);
        builder.setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(admin_home_activity.this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
                        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("admin_logdata",false);
                        editor.apply();
                        Intent intent = new Intent(admin_home_activity.this, com.example.vaxfinder.MainActivity.class);
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