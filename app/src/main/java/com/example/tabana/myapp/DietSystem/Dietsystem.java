package com.example.tabana.myapp.DietSystem;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tabana.myapp.R;

public class Dietsystem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietsystem);
        Fragment fr = new Main();
   getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Diet, fr).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.diet_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
