package com.example.tabana.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class login_coatch extends AppCompatActivity {
      TextView coach_up;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_coatch);



        coach_up=(TextView)findViewById(R.id.signup_coach);
        coach_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_patient = new Intent(login_coatch.this,signup_coatch.class);
                startActivity(signup_patient);

            }
        });
    }
}
