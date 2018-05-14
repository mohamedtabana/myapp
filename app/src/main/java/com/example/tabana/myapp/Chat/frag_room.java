 package com.example.tabana.myapp.Chat;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabana.myapp.MainActivity;
import com.example.tabana.myapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

 /**
 * A simple {@link Fragment} subclass.
 */

public class frag_room extends Fragment {
String CoatchID ="" ,patientID = "",CoatchorPatient="Patient";
boolean FromCoatch = false ;

     FirebaseRecyclerAdapter<Send,PassengerHolder> mAdapter;
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         if (getArguments() != null) {
             CoatchID = getArguments().getString("CoatchID");
             if(getArguments().getBoolean("FromCoatch") == true){
                 FromCoatch = true ;
                 patientID = getArguments().getString("patientID");
             }
         }
     }
    public frag_room() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_frag_room, container, false);


        final Chat A = (Chat) getActivity();

        DatabaseReference myref = null;

        if (FromCoatch == true){ //coatch

            myref = FirebaseDatabase.getInstance().getReference("Rooms").child(A.getphoneID())
                    .child(patientID);
            CoatchorPatient = "Coatch";
        }
        else { // patient
            myref  = FirebaseDatabase.getInstance().getReference("Rooms").child(CoatchID)
                    .child(A.getphoneID());

        }


        final EditText Message = (EditText) v.findViewById(R.id.editTextroom);
        ///
        final RecyclerView recycler = (RecyclerView) v.findViewById(R.id.Room_Recycle);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        final DatabaseReference finalMyref = myref;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(Message.getText().toString().isEmpty()) {

                 Toast.makeText(getActivity(), "Empty", Toast.LENGTH_SHORT).show();

             }

             else {



                 Date currentTime = Calendar.getInstance().getTime();
             finalMyref.push().setValue(new Send(Message.getText().toString(),currentTime.toString(),CoatchorPatient)).addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void aVoid) {
                     Message.setText("");



                 }
             });
}
            }
        });


        final DatabaseReference finalMyref1 = myref;
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    // Toast.makeText(getActivity(), "DataChanged", Toast.LENGTH_SHORT).show();

                    recycler.scrollToPosition(mAdapter.getItemCount() - 1);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        linearLayoutManager.setStackFromEnd(true);
        recycler.setLayoutManager(linearLayoutManager);

        recycler.setHasFixedSize(true);

        mAdapter = new FirebaseRecyclerAdapter<Send, PassengerHolder>(Send.class, R.layout.custom_room, PassengerHolder.class, finalMyref1) {
            @Override
            public void populateViewHolder(final PassengerHolder passengerViewHolder, final Send passenger, final int position) {

                if (FromCoatch == true) {

                    if (passenger.getFrom().equals("Coatch")) {
                        passengerViewHolder.a1.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) passengerViewHolder.a1.getLayoutParams();
                        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        passengerViewHolder.a1.setLayoutParams(params); //causes layout update
                        passengerViewHolder.a1.setText(passenger.getMessage());
                        passengerViewHolder.a2.setVisibility(View.GONE);
                    } else if (passenger.getFrom().equals("Patient")) {
                        passengerViewHolder.a2.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) passengerViewHolder.a2.getLayoutParams();
                        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        passengerViewHolder.a2.setLayoutParams(params); //causes layout update
                        passengerViewHolder.a2.setText(passenger.getMessage());
                        passengerViewHolder.a1.setVisibility(View.GONE);
                    }
                } else {
                    if (passenger.getFrom().equals("Patient")) {
                        passengerViewHolder.a1.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) passengerViewHolder.a1.getLayoutParams();
                        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        passengerViewHolder.a1.setLayoutParams(params); //causes layout update
                        passengerViewHolder.a1.setText(passenger.getMessage());
                        passengerViewHolder.a2.setVisibility(View.GONE);
                    } else if (passenger.getFrom().equals("Coatch")) {
                        passengerViewHolder.a2.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) passengerViewHolder.a2.getLayoutParams();
                        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        passengerViewHolder.a2.setLayoutParams(params); //causes layout update
                        passengerViewHolder.a2.setText(passenger.getMessage());
                        passengerViewHolder.a1.setVisibility(View.GONE);
                    }

                }
            }
        };

        recycler.setAdapter(mAdapter);



        return  v;
    }
     public static class PassengerHolder extends RecyclerView.ViewHolder {
        BubbleTextView a1;
         BubbleTextView a2;
         public PassengerHolder(View itemView) {
             super(itemView);
             a1 = (BubbleTextView) itemView.findViewById(R.id.bubbletext1);
             a2 = (BubbleTextView) itemView.findViewById(R.id.bubbletext2);

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
