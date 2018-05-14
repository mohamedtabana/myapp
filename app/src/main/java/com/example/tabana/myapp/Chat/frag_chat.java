package com.example.tabana.myapp.Chat;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabana.myapp.R;
import com.example.tabana.myapp.home_page;
import com.example.tabana.myapp.model.Patient;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class frag_chat extends Fragment {



    public frag_chat() {
        // Required empty public constructor
    }
    FirebaseRecyclerAdapter<Patient,PassengerHolder> mAdapter;
    boolean FromCoatch;
    ArrayList<String> PatientID  ;
    ArrayList<String> PatientName  ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_frag_chat, container, false);


         Chat A = (Chat) getActivity();
        FromCoatch = A.getFromCoatch();

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("coatch");
        if(FromCoatch == true){

            myref = FirebaseDatabase.getInstance().getReference("Rooms").child(A.getphoneID());
            PatientID = new ArrayList<>();
            PatientName = new ArrayList<>();
            myref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                    for(DataSnapshot Snapshot : dataSnapshot.getChildren() ) {


                        PatientID.add(Snapshot.getKey());
                    }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        RecyclerView recycler = (RecyclerView) v.findViewById(R.id.Chat_Recycle);
        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        final DatabaseReference finalMyref = myref;
        mAdapter = new FirebaseRecyclerAdapter<Patient, PassengerHolder>(Patient.class, R.layout.chat_recycle, PassengerHolder.class, finalMyref) {
            @Override
            public void populateViewHolder(final PassengerHolder passengerViewHolder, final Patient passenger, final int position) {

                if (FromCoatch == true){  // in Couch position

                    DatabaseReference Name = FirebaseDatabase.getInstance().getReference("patient").child(PatientID.get(position));
                    Name.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                Patient a = dataSnapshot.getValue(Patient.class);
                                passengerViewHolder.a1.setText(a.getName());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


  //Done => ////// باقي تنقل البيانات دي وتروح ع الشات ومتنساش تنقل الtrue بتاع الCoach
                    passengerViewHolder.Image.setOnClickListener(new View.OnClickListener() { // لو عاوز تدوس علي عنصر فقط
                        @Override
                        public void onClick(View view) {


                            Bundle bundle = new Bundle();
                            bundle.putString("patientID", PatientID.get(position));
                            bundle.putBoolean("FromCoatch",true);
                            Fragment myObj = new frag_room();
                            myObj.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.Chat_Fragment, myObj).addToBackStack(null).commit();
                        }
                    });





                    StorageReference filepath = FirebaseStorage.getInstance().getReference("patient").child(PatientID.get(position));
                    filepath.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Picasso.with(getActivity())
                                        .load(task.getResult()).error(R.drawable.john)
                                        // .resize(420, 350)                        // optional
                                        .into(passengerViewHolder.Image); //ivTest = > imageview
                            }
                        }
                    });

                }
                else { // in patient postions
                    passengerViewHolder.a1.setText(passenger.getName());
                    //.......
                    passengerViewHolder.Image.setOnClickListener(new View.OnClickListener() { // لو عاوز تدوس علي عنصر فقط
                        @Override
                        public void onClick(View view) {


                            Bundle bundle = new Bundle();
                            bundle.putString("CoatchID", passenger.getId());
                            Fragment myObj = new frag_room();
                            myObj.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.Chat_Fragment, myObj).addToBackStack(null).commit();
                        }
                    });
                    StorageReference filepath = FirebaseStorage.getInstance().getReference("coatch").child(passenger.getId());
                    filepath.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Picasso.with(getActivity())
                                        .load(task.getResult()).error(R.drawable.john)
                                        // .resize(420, 350)                        // optional
                                        .into(passengerViewHolder.Image); //ivTest = > imageview
                            }
                        }
                    });

                }
            }
        };
        recycler.setAdapter(mAdapter);

        return v;
    }
public static class PassengerHolder extends RecyclerView.ViewHolder {
    TextView a1;
    ImageView Image ;

    public PassengerHolder(View itemView) {
        super(itemView);
        a1 = (TextView) itemView.findViewById(R.id.Couch_Name);
        Image = (ImageView) itemView.findViewById(R.id.Couch_image);

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
