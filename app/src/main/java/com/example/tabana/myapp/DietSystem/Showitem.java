package com.example.tabana.myapp.DietSystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabana.myapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class Showitem extends Fragment {
    int place = 1;

    public Showitem() {
        // Required empty public constructor
    }
    FirebaseRecyclerAdapter<Data,PassengerHolder> mAdapter;
int Position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            Position = getArguments().getInt("Position");
        }
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_showitem, container, false);
        DatabaseReference myref = null;
        if (Position == 0){
            myref = FirebaseDatabase.getInstance().getReference("Food").child("Breakfast");
        }
        else if (Position == 1 ) {
            myref = FirebaseDatabase.getInstance().getReference("Food").child("Lunch");
        }
        else if (Position == 2 ) {
            myref = FirebaseDatabase.getInstance().getReference("Food").child("Dinner");
        }
        else if (Position == 3 ) {
            myref = FirebaseDatabase.getInstance().getReference("Food").child("ExtraMeal1");
        }
        else if (Position == 4 ) {
            myref = FirebaseDatabase.getInstance().getReference("Food").child("ExtraMeal2");
        }
        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.Rec_item);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FirebaseRecyclerAdapter<Data, PassengerHolder>(Data.class, R.layout.custom_recycle, PassengerHolder.class,myref) {
            @Override
            public void populateViewHolder(PassengerHolder passengerViewHolder, final Data passenger, final int position) {
                passengerViewHolder.a1.setText(passenger.getName());
                passengerViewHolder.a2.setText(passenger.getCalories());
                passengerViewHolder.a3.setText(passenger.getQuantity());
                Picasso.with(getActivity())
                        .load(passenger.getUploadimage()).error(R.drawable.disconnected)
                        // .resize(420, 350)                        // optional
                        .into(passengerViewHolder.a4); //ivTest = > imageview
                //.......

                passengerViewHolder.plus.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        DatabaseReference reference = null ;
                        if (Position == 0) {
                             reference = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                                    .child("Breakfast").child(""+place+"");
                            if(place == 2){
                                place =1;
                            }
                            else {
                                place = 2;
                            }
                        }
                        else if (Position == 1 ) {
                             reference = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                                    .child("Lunch").child(""+place+"");
                            if(place == 2){
                                place =1;
                            }
                            else {
                                place = 2;
                            }
                        }
                        else if (Position == 2 ) {
                             reference = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                                    .child("Dinner").child(""+place+"");
                            if(place == 2){
                                place =1;
                            }
                            else {
                                place = 2;
                            }
                        }
                        else if (Position == 3 ) {
                             reference = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                                    .child("ExtraMeal1").child(""+place+"");
                            if(place == 2){
                                place =1;
                            }
                            else {
                                place = 2;
                            }
                        }
                        else if (Position == 4 ) {
                             reference = FirebaseDatabase.getInstance().getReference("ChoosenFood").child(Dietsystem.getphoneID())
                                    .child("ExtraMeal2").child(""+place+"");
                            if(place == 2){
                                place =1;
                            }
                            else {
                                place = 2;
                            }
                        }

                        reference.setValue(new Addinbox(passenger.getUploadimage(),passenger.getCalories()));




                    }
                });
            }
        };
        recycler.setAdapter(mAdapter);
       return v;
    }
    public static class PassengerHolder extends RecyclerView.ViewHolder {
        TextView a1,a2,a3;
        ImageView a4,plus;

        public PassengerHolder(View itemView) {
            super(itemView);
            a1 = (TextView) itemView.findViewById(R.id.Diet_Sub);
            a2 = (TextView) itemView.findViewById(R.id.rec_Calories);
            a3 = (TextView) itemView.findViewById(R.id.rec_quantity);
            a4 = (ImageView) itemView.findViewById(R.id.Diet_image);
            plus = (ImageView) itemView.findViewById(R.id.plus);
//            itemView.setOnClickListener(new View.OnClickListener() {  //دي للفييو بالكامل
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), "Toast"+getPosition(), Toast.LENGTH_SHORT).show();
//                    Post p = arrpost.get(getPosition());
//                    Log.d("tag4"," name => "+p.getName() +" age=> "+p.getAge()+" phone => "+p.getPhone());
//
//                }
//            });
        }
    }
}
