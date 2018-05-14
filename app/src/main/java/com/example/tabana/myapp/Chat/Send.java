package com.example.tabana.myapp.Chat;

/**
 * Created by microprocess on 2018-05-13.
 */

public class Send {
    String message , date , from ;

    public Send(String message, String date, String from) {
        this.message = message;
        this.date = date;
        this.from = from;
    }

    public Send() {
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }
}
