package com.example.vaxfinder.user.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vaxfinder.R;

public class user_log_activity extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button logButton;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logButton = findViewById(R.id.logButton);
        signup = findViewById(R.id.signupTxt);
        String fullText = "Not yet registered? SignUp Now";
        SpannableString spannableString = new SpannableString(fullText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {
                Intent intent = new Intent(user_log_activity.this, com.example.vaxfinder.user.Activity.signup.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        spannableString.setSpan(clickableSpan,fullText.indexOf("SignUp Now"),fullText.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signup.setText(spannableString);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
        signup.setHighlightColor(Color.TRANSPARENT);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.getText().toString().equals("7559900403") && password.getText().toString().equals("1234")){
                    Toast.makeText(user_log_activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("user_logdata",true);
                    editor.apply();
                    Intent intent = new Intent(user_log_activity.this, home.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(user_log_activity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}