package com.example.vaxfinder.admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaxfinder.R;

public class admin_login_activity extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button logButton;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logButton = findViewById(R.id.logButton);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.getText().toString().equals("7559900403") && password.getText().toString().equals("1234")){
                    Toast.makeText(admin_login_activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(admin_login_activity.this, admin_home_activity.class);
                    startActivity(intent);
                    finish();
                }else if(userName.getText().toString().equals("7559900403")){
                    Toast.makeText(admin_login_activity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(admin_login_activity.this,"Invalid User Name",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}