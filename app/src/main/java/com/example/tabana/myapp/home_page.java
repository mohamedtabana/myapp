package com.example.tabana.myapp;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.support.v4.view.PagerAdapter;
import android.widget.TextView;






public class home_page extends AppCompatActivity {

    TextView BMI;


    ViewPager silderrview;
    LinearLayout dolayout;
    private TextView[] dots ;
    private silder_adapter silder_adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        BMI=(TextView)findViewById(R.id.bmi);
            BMI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent home_page= new Intent(home_page.this, Calculator_BMI.class);
                    startActivity(home_page);
                }
            });

        silderrview = (ViewPager)findViewById(R.id.silder_view);
        dolayout = (LinearLayout)findViewById(R.id.doslayout);

        silder_adapter = new silder_adapter(this);
        silderrview.setAdapter(silder_adapter);


        adddots ();

    }

    public  void  adddots (){


        dots = new  TextView[3];
        for (int i =0;i< dots.length;i++){

            dots[i]=new TextView(this);
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
}
