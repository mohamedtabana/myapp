package com.example.tabana.myapp;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabana.myapp.Chat.Chat;
import com.example.tabana.myapp.DietSystem.Dietsystem;
import com.example.tabana.myapp.model.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class home_page extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    CircleImageView profileimage;
    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    private  static  int Gallary_intent = 2 ;
    ViewPager silderrview;
    LinearLayout dolayout;
    private TextView[] dots;
    private silder_adapter silder_adapter;
    private  String phoneID;
    boolean FromCoatch = false;
    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        intialize();
        Bundle A = getIntent().getExtras();
        if (A != null)
        {
            phoneID = A.getString("PhoneID");

            StorageReference filepath = FirebaseStorage.getInstance().getReference("patient").child(phoneID);
            if(A.getBoolean("fromCoatch") == true){

                filepath = FirebaseStorage.getInstance().getReference("coatch").child(phoneID);
                FromCoatch = true;
            }


            filepath.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Picasso.with(home_page.this)
                                .load(task.getResult()).error(R.drawable.john)
                                // .resize(420, 350)                        // optional
                                .into(profileimage); //ivTest = > imageview
                    }
                }
            });


        }

        ///////////////////////////////////////////////////////////////////Navigation drawer


        // Setup drawer view
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close);

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        profileimage.setOnClickListener(new View.OnClickListener() { // رفع الصورة
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Gallary_intent);


            }
        });

        //navigation drawer item changer programmatically
        // get menu from navigationView
        Menu menu = nvDrawer.getMenu();
        final MenuItem nav_name = menu.findItem(R.id.nav_first_fragment);
        final MenuItem nav_phone = menu.findItem(R.id.nav_third_fragment);
        final MenuItem nav_email = menu.findItem(R.id.nav_second_fragment);
        nav_phone.setTitle(phoneID);
        DatabaseReference getdata = FirebaseDatabase.getInstance().getReference("patient").child(phoneID);
        if(FromCoatch == true){
            getdata = FirebaseDatabase.getInstance().getReference("coatch").child(phoneID);
        }
        getdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Patient patient = dataSnapshot.getValue(Patient.class);
                nav_name.setTitle(patient.getName());
                nav_email.setTitle(patient.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //////////////////////////////////////////////////////////////
        silderrview = (ViewPager) findViewById(R.id.silder_view);
        dolayout = (LinearLayout) findViewById(R.id.doslayout);

        silder_adapter = new silder_adapter(this);
        silderrview.setAdapter(silder_adapter);


        adddots();

    }

    public void adddots() {


        dots = new TextView[3];
        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.white));
            dolayout.addView(dots[i]);

        }

    }

    ViewPager.OnPageChangeListener Viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void Bmi(View view) {
        Intent A = new Intent(home_page.this, Calculator_BMI.class);
        startActivity(A);
    }
    public void Calorie(View view) {
        Intent A = new Intent(home_page.this, calculator_bmr.class);
        startActivity(A);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to Read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Gallary_intent && resultCode == RESULT_OK){
            final ProgressDialog mDialog = new ProgressDialog(home_page.this);
            mDialog.setMessage("  please Waiting ...for Upload your profile image ");
            mDialog.show();
            final Uri uri = data.getData();
            StorageReference filepath = FirebaseStorage.getInstance().getReference("patient").child(phoneID);
            if (FromCoatch == true){
                filepath = FirebaseStorage.getInstance().getReference("coatch").child(phoneID);
            }
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    profileimage.setImageURI(uri);
                    mDialog.dismiss();
                    Toast.makeText(home_page.this, "Success Upload", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mDialog.dismiss();
                    Toast.makeText(home_page.this, "  Faild Upload..", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void intialize(){

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        View headerview = nvDrawer.getHeaderView(0);
        profileimage = (CircleImageView) headerview.findViewById(R.id.profile_image);


    }

    public void Dietsystem(View view) {
        Intent a = new Intent(this, Dietsystem.class);
        a.putExtra("phoneID",phoneID);
        startActivity(a);
    }

    public void Chat(View view) {
        Intent A = new Intent(this,Chat.class);
        A.putExtra("phoneID",phoneID);
        A.putExtra("FromCoatch",FromCoatch);
        startActivity(A);
    }
}
