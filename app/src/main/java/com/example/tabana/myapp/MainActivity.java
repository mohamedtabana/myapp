package com.example.tabana.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login_patient, login_coatch, signup_patient, signup_coatch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_patient = (Button) findViewById(R.id.btn1_login_patient);


        login_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login_patient = new Intent(MainActivity.this, login_patient.class);
                startActivity(login_patient);

            }
        });

        login_coatch = (Button) findViewById(R.id.btn2_login_coatch);

        login_coatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_coatch = new Intent(MainActivity.this, login_coatch.class);
                startActivity(login_coatch);
            }
        });

    }
}