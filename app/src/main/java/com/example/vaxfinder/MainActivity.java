package com.example.vaxfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vaxfinder.admin.Activity.admin_login_activity;
import com.example.vaxfinder.user.Activity.user_log_activity;

public class MainActivity extends AppCompatActivity {

    Button AdminLogBtn,userLogBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdminLogBtn = findViewById(R.id.AdminLogBtn);
        userLogBtn = findViewById(R.id.UserLogBtn);

        AdminLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, admin_login_activity.class);
                startActivity(intent);
                finish();
            }
        });

        userLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, user_log_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}