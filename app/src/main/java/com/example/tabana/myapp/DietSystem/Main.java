package com.example.tabana.myapp.DietSystem;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tabana.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main extends Fragment {


    public Main() {
        // Required empty public constructor
    }


    String [] sub;
    String [] details;
    int [] Image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) v.findViewById(R.id.list_main);




        sub  = new String[]{"Breakfast", "Lunch", "Dinner", "Extra meal 1", "Extra meal 2"};
        details = new String[]{"Meals during the morning", "Meals during the afternoon", "Meals during the night",
                "Meals during the AnyTime", "Meals during the AnyTime"};
        Image = new int[]{R.drawable.cereal, R.drawable.roastturkey, R.drawable.food, R.drawable.diet, R.drawable.groceries};

        Myadapter myadapter = new Myadapter(getActivity(),0,sub);
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new Showitem();
             getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Diet, fragment).addToBackStack(null).commit();
                Bundle bundle = new Bundle();
                bundle.putInt("Position",i);
// set MyFragment Arguments

                fragment.setArguments(bundle);
            }
        });

        return v;
    }

    class Myadapter extends ArrayAdapter<String> {
        public Myadapter(@NonNull Context context, int resource, @NonNull String[] objects) {
            super(context, 0 , objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inf = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View a = inf.inflate(R.layout.custum_list,parent,false);
            TextView subj = (TextView) a.findViewById(R.id.Diet_Sub);
            TextView detail = (TextView) a.findViewById(R.id.Diet_Detail);
            ImageView imageView = (ImageView) a.findViewById(R.id.Diet_image);
            subj.setText(sub[position]);
            detail.setText(details[position]);
            imageView.setImageResource(Image[position]);
            return a;
        }
    }

}
