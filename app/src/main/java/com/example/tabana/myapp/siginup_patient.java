package com.example.tabana.myapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tabana.myapp.model.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class siginup_patient extends AppCompatActivity {

    EditText signup_patient_name , signup_patient_email , signup_patient_phone , signup_parient_password ;
    Button signup_btn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siginup_patient);

       signup_patient_name = (EditText)findViewById(R.id.siginup_pa_name);
       signup_patient_email = (EditText)findViewById(R.id.siginup_pa_email);
       signup_patient_phone = (EditText)findViewById(R.id.siginup_pa_phone);
       signup_parient_password =(EditText)findViewById(R.id.siginup_pa_password);
       signup_btn = (Button)findViewById(R.id.signup_pa_btn);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_patient= database.getReference("patient");

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(siginup_patient.this);
                mDialog.setMessage("please waiting .....");
                mDialog.show();


                table_patient.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(signup_patient_phone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(siginup_patient.this, "phone number already register", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            mDialog.dismiss();
                          Patient patient = new Patient(signup_patient_name.getText().toString(),signup_parient_password.getText().toString(),signup_patient_email.getText().toString(),signup_patient_phone.getText().toString());
                            table_patient.child(signup_patient_phone.getText().toString()).setValue(patient);
                            Toast.makeText(siginup_patient.this, "sign up successfuly !", Toast.LENGTH_SHORT).show();
                            finish();


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
