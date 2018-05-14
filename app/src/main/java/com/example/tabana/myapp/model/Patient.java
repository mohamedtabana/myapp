package com.example.tabana.myapp.model;



public class Patient {

    private String name;
    private String password;
    private String email ;
    private String id;


    public Patient() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }

    public Patient(String name, String password, String email , String id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id ;







    }
}
