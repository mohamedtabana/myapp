package com.example.tabana.myapp.DietSystem;

/**
 * Created by microprocess on 2018-04-24.
 */

public class Data {
    private String fireID,name,calories,quantity,uploadimage;

    public Data() {
    }

    public Data(String fireID, String name, String calories, String quantity, String uploadimage) {
        this.fireID = fireID;
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
        this.uploadimage = uploadimage;
    }

    public String getFireID() {
        return fireID;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUploadimage() {
        return uploadimage;
    }
}
