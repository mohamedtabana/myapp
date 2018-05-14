package com.example.tabana.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabana.myapp.model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_coatch extends AppCompatActivity {
    TextView up;
    EditText edtphone, edtpassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_coatch);


        up = (TextView) findViewById(R.id.signup_coach);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signup_patient = new Intent(login_coatch.this , signup_coatch.class);
                startActivity(signup_patient);


            }
        });
        edtphone = (EditText) findViewById(R.id.login_phone);
        edtpassword = (EditText) findViewById(R.id.login_password);
        btnSignIn = (Button) findViewById(R.id.loginBtn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(login_coatch.this);
                mDialog.setMessage("please waiting .....");
                mDialog.show();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference table_patient = database.getReference("coatch").child(edtphone.getText().toString());

                table_patient.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Patient patient = dataSnapshot.getValue(Patient.class);

                            if (patient.getPassword().equals(edtpassword.getText().toString())) {
                                mDialog.dismiss();
                                Toast.makeText(login_coatch.this, "Sign in SuccessFully and wall done !!", Toast.LENGTH_SHORT).show();
                                Intent login_patient = new Intent(login_coatch.this, home_page.class);
                                login_patient.putExtra("PhoneID", edtphone.getText().toString());
                                login_patient.putExtra("fromCoatch",true);
                                startActivity(login_patient);

                            }
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
