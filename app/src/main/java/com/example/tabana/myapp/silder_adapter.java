package com.example.tabana.myapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class silder_adapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public silder_adapter(Context context) {
        this.context = context;
    }

    public int[] silder_image1 = {
            R.drawable.food_item,
            R.drawable.code,
            R.drawable.pc

    };
    public String[] Silder_heading = {
            "food_item",
            "coach for diet",
            "more information"


    };



    public int getCount() {
        return Silder_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.silder_layout,container,false);

        ImageView silder_image_view = (ImageView)view.findViewById(R.id.imageView);
        TextView silder_heading = (TextView)view.findViewById(R.id.textView_hader1);


        silder_image_view.setImageResource(silder_image1[position]);
        silder_heading.setText(Silder_heading[position]);

        container.addView(view);


        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
