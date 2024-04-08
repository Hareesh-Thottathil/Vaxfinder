package com.example.vaxfinder.user.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Domain.PersonList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowDetailsActivity extends AppCompatActivity {
Button downloadCertificateBtn;
FloatingActionButton backBtn;
private TextView txtBookingData,txtHaii,txtVaccine1,txtCenter1,txtDose1,txtVaccine2,txtCenter2,txtDose2;
private PersonList object,arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        initView();
        getBundle();
    }

    private void getBundle() {
        object= (PersonList) getIntent().getSerializableExtra("object");
        txtBookingData.setText(defaultTxt(txtBookingData));
        txtHaii.setText("Haii " + getIntent().getStringExtra("haii") + ",");
        txtVaccine1.setText(defaultTxt(txtVaccine1));
        txtCenter1.setText(defaultTxt(txtCenter1));
        txtDose1.setText(defaultTxt(txtDose1));
        txtVaccine2.setText(defaultTxt(txtVaccine2));
        txtCenter2.setText(defaultTxt(txtCenter2));
        txtDose2.setText(defaultTxt(txtDose2));

        downloadCertificateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowDetailsActivity.this, "Downloading", Toast.LENGTH_SHORT).show();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailsActivity.this,home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String defaultTxt(TextView txt) {
        return (txt.getText().toString() + "Unknown");
    }

    private void initView() {
        downloadCertificateBtn = findViewById(R.id.downloadCertificateBtn);
        backBtn = findViewById(R.id.backBtn);
        txtBookingData = findViewById(R.id.txtBookingDate);
        txtHaii = findViewById(R.id.txtHaii);
        txtVaccine1 = findViewById(R.id.txtVaccine1);
        txtCenter1 = findViewById(R.id.txtcenter1);
        txtDose1 = findViewById(R.id.txtDose1);
        txtVaccine2 = findViewById(R.id.txtVaccine2);
        txtCenter2 = findViewById(R.id.txtcenter2);
        txtDose2 = findViewById(R.id.txtDose2);

    }
}