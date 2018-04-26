package com.example.tabana.myapp.DietSystem;

/**
 * Created by microprocess on 2018-04-26.
 */

public class Addinbox {
    String image;
    String calories;



    public Addinbox(String image, String calories) {
        this.image = image;
        this.calories = calories;
    }

    public Addinbox() {
    }

    public String getImage() {
        return image;
    }
    public String getCalories() {
        return calories;
    }
}
