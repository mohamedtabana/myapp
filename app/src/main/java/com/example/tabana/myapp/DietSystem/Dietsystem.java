package com.example.tabana.myapp.DietSystem;

import android.app.Dialog;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabana.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Dietsystem extends AppCompatActivity {
static String phoneID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietsystem);
        Bundle a = getIntent().getExtras();
        if (a != null){
            phoneID = a.getString("phoneID");
        }
        Fragment fr = new Main();
   getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Diet, fr).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.diet_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.inbox){
            final Dialog dialog = new Dialog(this,R.style.PauseDialog);
            dialog.setContentView(R.layout.dialog_food);
            final ImageView breakfast1 = (ImageView) dialog.findViewById(R.id.breakimage1);
            final ImageView breakfast2 = (ImageView) dialog.findViewById(R.id.breakimage2);
            final ImageView lunchimage1 = (ImageView) dialog.findViewById(R.id.lunchimage1);
            final ImageView lunchimage2 = (ImageView) dialog.findViewById(R.id.lunchimage2);
            final ImageView Dinnerimage1 = (ImageView) dialog.findViewById(R.id.Dinnerimage1);
            final ImageView Dinnerimage2 = (ImageView) dialog.findViewById(R.id.Dinnerimage2);
            final ImageView Extramealimage1 = (ImageView) dialog.findViewById(R.id.Extramealimage1);
            final ImageView Extramealimage2 = (ImageView) dialog.findViewById(R.id.Extramealimage2);
            final ImageView Extrameal2image1 = (ImageView) dialog.findViewById(R.id.Extrameal2image1);
            final ImageView Extrameal2image2 = (ImageView) dialog.findViewById(R.id.Extrameal2image2);

            final TextView breakfastcalories = (TextView) dialog.findViewById(R.id.breakfastcalories);
            final int[] sum = new int[2];
            final TextView lunchcalories = (TextView) dialog.findViewById(R.id.lunchcalories);
            final int[] sum2 = new int[2];
            final TextView dinnercalories = (TextView) dialog.findViewById(R.id.dinnercalories);
            final int[] sum3 = new int[2];
            final TextView Extramealcalories = (TextView) dialog.findViewById(R.id.Extramealcalories);
            final int[] sum4 = new int[2];
            final TextView Extrameal2calories = (TextView) dialog.findViewById(R.id.Extrameal2calories);
            final int[] sum5 = new int[2];



            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("Breakfast").child("1");
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                                     Addinbox p = dataSnapshot.getValue(Addinbox.class);
                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(breakfast1); //ivTest = > imageview
                        sum[0] = Integer.parseInt(p.getCalories());

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("Breakfast").child("2");
            reference2.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(breakfast2); //ivTest = > imageview
                        sum[1] = Integer.parseInt(p.getCalories());
                        breakfastcalories.setText((sum[0]+sum[1])+"\n Calories");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("Lunch").child("1");
           reference3.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(lunchimage1); //ivTest = > imageview
                        sum2[0]= Integer.parseInt(p.getCalories());

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("Lunch").child("2");
            reference4.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(lunchimage2); //ivTest = > imageview
                        sum2[1]= Integer.parseInt(p.getCalories());
                        lunchcalories.setText((sum2[0]+sum2[1])+"\n Calories");

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference reference5 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("Dinner").child("1");
            reference5.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(Dinnerimage1); //ivTest = > imageview
                        sum3[0] = Integer.parseInt(p.getCalories());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference reference6 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("Dinner").child("2");
            reference6.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(Dinnerimage2); //ivTest = > imageview
                        sum3[1]= Integer.parseInt(p.getCalories());
                        dinnercalories.setText((sum3[0]+sum3[1])+"\n Calories");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            DatabaseReference reference7 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("ExtraMeal1").child("1");
            reference7.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(Extramealimage1); //ivTest = > imageview
                        sum4[0] = Integer.parseInt(p.getCalories());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference reference8 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("ExtraMeal1").child("2");
            reference8.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(Extramealimage2); //ivTest = > imageview
                        sum4[1]= Integer.parseInt(p.getCalories());
                        Extramealcalories.setText((sum4[0]+sum4[1])+"\n Calories");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            DatabaseReference reference9 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("ExtraMeal2").child("1");
            reference9.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(Extrameal2image1); //ivTest = > imageview
                        sum5[0] = Integer.parseInt(p.getCalories());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            DatabaseReference reference10 = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                    .child("ExtraMeal2").child("2");
            reference10.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        Addinbox p = dataSnapshot.getValue(Addinbox.class);

                        Picasso.with(Dietsystem.this)
                                .load(p.getImage()).error(R.drawable.disconnected)
                                // .resize(420, 350)                        // optional
                                .into(Extrameal2image2); //ivTest = > imageview
                        sum5[1]= Integer.parseInt(p.getCalories());
                        Extrameal2calories.setText((sum5[0]+sum5[1])+"\n Calories");



                        dialog.setTitle("Summation ==> "+(sum[0]+sum[1]+sum2[0]+sum2[1]+sum3[0]+sum3[1]+
                                sum4[0]+sum4[1]+sum5[0]+sum5[1])+" Calories");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //Toast.makeText(this, phoneID, Toast.LENGTH_SHORT).show();

            
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    public static String getphoneID(){
        return phoneID;
    }
}
