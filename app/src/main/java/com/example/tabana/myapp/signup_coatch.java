package com.example.tabana.myapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tabana.myapp.model.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup_coatch extends AppCompatActivity {
    EditText signup_coatch_name , signup_coatch_email , signup_coatch_phone , signup_coatch_password ;
    Button signup_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_coatch);

    signup_coatch_name = (EditText)findViewById(R.id.fullName);
    signup_coatch_email = (EditText)findViewById(R.id.userEmailId);
    signup_coatch_phone = (EditText)findViewById(R.id.mobileNumber);
    signup_coatch_password =(EditText)findViewById(R.id.password);
    signup_btn = (Button) findViewById(R.id.signUpBtn);


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_patient= database.getReference("coatch");

        signup_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final ProgressDialog mDialog = new ProgressDialog(signup_coatch.this);
            mDialog.setMessage("please waiting .....");
            mDialog.show();


            table_patient.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(signup_coatch_phone.getText().toString()).exists())
                    {
                        mDialog.dismiss();
                        Toast.makeText(signup_coatch.this, "phone number already register", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        mDialog.dismiss();
                        Patient patient = new Patient(signup_coatch_name.getText().toString(),signup_coatch_password.getText().toString(),signup_coatch_email.getText().toString(),signup_coatch_phone.getText().toString());
                        table_patient.child(signup_coatch_phone.getText().toString()).setValue(patient);
                        Toast.makeText(signup_coatch.this, "sign up successfuly !", Toast.LENGTH_SHORT).show();
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
