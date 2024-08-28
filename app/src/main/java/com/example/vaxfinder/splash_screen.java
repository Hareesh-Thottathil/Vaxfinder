package com.example.vaxfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.vaxfinder.MainActivity;
import com.example.vaxfinder.R;
import com.example.vaxfinder.admin.Activity.admin_home_activity;
import com.example.vaxfinder.user.Activity.home;

public class splash_screen extends AppCompatActivity {

    boolean user_logdata,admin_logdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("MyPrefs",MODE_PRIVATE);
                user_logdata = prefs.getBoolean("userlog_data",false);
                admin_logdata =prefs.getBoolean("admin_logdata",false);
                Intent intent;
                if(user_logdata){
                    intent = new Intent(splash_screen.this, home.class);
                } else if (admin_logdata) {
                    intent = new Intent(splash_screen.this, admin_home_activity.class);
                }else {
                    intent = new Intent(splash_screen.this, MainActivity.class);
                }

                startActivity(intent);
                finish();
            }
        },3000);
    }
}