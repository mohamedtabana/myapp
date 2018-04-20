package com.example.tabana.myapp;

import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.*;

public class calculator_bmr extends AppCompatActivity {
    static int medicalId = -1;
    TextView activityBtn, littleToNoExercise, lightExercise, ModerateExercise, HeavyExercise, veryHeavyExercise;
    RadioButton femaleRadioButton, maleRadioButton;
    EditText ageEditText, weightEditText, heightEditText;
    LinearLayout one,two,three,four,five;
    Integer gender;
     float  BMRFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_bmr);
        femaleRadioButton =(RadioButton) findViewById(R.id.femaleRadioButton);
        maleRadioButton = (RadioButton)findViewById(R.id.maleRadioButton);
        littleToNoExercise = (TextView) findViewById(R.id.littleToNoExercise);
        lightExercise = (TextView)findViewById(R.id.lightExercise);
        ModerateExercise = (TextView)findViewById(R.id.ModerateExercise);
        HeavyExercise = (TextView)findViewById(R.id.HeavyExercise);
        veryHeavyExercise =(TextView) findViewById(R.id.veryHeavyExercise);
        one =(LinearLayout) findViewById(R.id.one);
        two =(LinearLayout)findViewById(R.id.two);
        three =(LinearLayout)findViewById(R.id.three);
        four =(LinearLayout)findViewById(R.id.four);
        five =(LinearLayout)findViewById(R.id.five);
        gender = 0;
        activityBtn =(TextView) findViewById(R.id.activityBtn);
        ageEditText =(EditText) findViewById(R.id.ageEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        activityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivity();
            }
        });
    }

    public void showActivity() {
        final Menu[] menu = new Menu[1];

        PopupMenu popup = new PopupMenu(calculator_bmr.this, activityBtn);
        popup.getMenuInflater()
                .inflate(R.menu.main, popup.getMenu());
        menu[0] = popup.getMenu();
        ;
        menu[0].add(0, Menu.FIRST + 1, Menu.FIRST + 1, "little To No Exercise").setTitleCondensed(1 + "");
        menu[0].add(0, Menu.FIRST + 1, Menu.FIRST + 1, "light Exercise").setTitleCondensed(2 + "");
        menu[0].add(0, Menu.FIRST + 1, Menu.FIRST + 1, "Moderate Exercise").setTitleCondensed(3 + "");
        menu[0].add(0, Menu.FIRST + 1, Menu.FIRST + 1, "Heavy Exercise").setTitleCondensed(4 + "");
        menu[0].add(0, Menu.FIRST + 1, Menu.FIRST + 1, "Very heavy Exercise").setTitleCondensed(5 + "");

        //  menu[0].add(0, Menu.FIRST, Menu.FIRST, "1").setCheckable(false);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {

                medicalId = Integer.parseInt((String) item.getTitleCondensed());
                Toast.makeText(
                        calculator_bmr.this,
                        "You Clicked : " + item.getTitle() + item.getTitleCondensed(),
                        Toast.LENGTH_SHORT
                ).show();
                activityBtn.setText(item.getTitle());
                return true;
            }
        });
        popup.show();
    }

    public void onRadioButtonClicked(View view) {
        Boolean Checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.femaleRadioButton:
                if (Checked)
                    gender = 1;
                break;
            case R.id.maleRadioButton:
                if (Checked)
                    gender = 2;
                break;
        }
    }

    public void Calculate(View view) {
        if (gender == 1) {
            BMRFloat = BMRForWoman(Integer.parseInt(weightEditText.getText().toString()), Integer.parseInt(heightEditText.getText().toString()), Integer.parseInt(ageEditText.getText().toString()));
        } else if (gender == 2) {
            BMRFloat = BMRForMan(Integer.parseInt(weightEditText.getText().toString()), Integer.parseInt(heightEditText.getText().toString()), Integer.parseInt(ageEditText.getText().toString()));
        }
        littleToNoExercise.setText(BMRFloat * 1.2 + "");
        lightExercise.setText(BMRFloat * 1.375 + "");
        ModerateExercise.setText(BMRFloat * 1.55 + "");
        HeavyExercise.setText(BMRFloat * 1.725 + "");
        veryHeavyExercise.setText(BMRFloat * 1.9 + "");
        if (medicalId==1)
        {
            one.setVisibility(View.VISIBLE);
        }
        else if (medicalId==2)
        {
            two.setVisibility(View.VISIBLE);
        }
        else if (medicalId==3)
        {
            three.setVisibility(View.VISIBLE);
        }
        else if (medicalId==4)
        {
            four.setVisibility(View.VISIBLE);
        }
        else if (medicalId==5)
        {
            five.setVisibility(View.VISIBLE);
        }
    }

    public float BMRForWoman(int weight, int height, int age) {
        float BMR = (float) (10 * weight + 6.25 * height - 5 * age + 161);
        return BMR;
    }

    public float BMRForMan(int weight, int height, int age) {
        float BMR = (float) (10 * weight + 6.25 * height - 5 * age + 5);
        return BMR;
    }

}

