package com.example.miras.emailapp;

import java.util.Date;

/**
 * Created by Miras on 20.09.2017.
 */

public class Mail {
    public int ID;
    public String sender;
    public String date;
    public String subject;
    public String title;
    public String content;

    public Mail (){}

    public Mail (int ID, String sender, String date, String title, String subject, String content) {
        this.ID = ID;
        this.sender = sender;
        this.date = date;
        this.title = title;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return this.date + " : " + this.sender + " : " + this.title;
    }
}
