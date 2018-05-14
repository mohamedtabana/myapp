package com.example.tabana.myapp.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabana.myapp.DietSystem.Showitem;
import com.example.tabana.myapp.R;
import com.example.tabana.myapp.model.Patient;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {
private  static  String phoneID;
private  static  boolean FromCoatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
     getSupportFragmentManager().beginTransaction().replace(R.id.Chat_Fragment,new frag_chat()).commit();
        Bundle a = getIntent().getExtras();
        if (a != null){
            phoneID = a.getString("phoneID");
            FromCoatch = a.getBoolean("FromCoatch");
        }

    }


    public static String getphoneID(){
        return phoneID;
    }
    public static boolean getFromCoatch(){
        return FromCoatch;
    }
}
