package com.example.miras.chatapp;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Miras on 24.10.2017.
 */

public class Message {
    private String messageText;
    private String messageUser;
    private String messageTime;


    public Message() { }

    public Message(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime =  DateFormat.getDateTimeInstance().format(new Date());
    }

    public Message(String messageText, String messageUser, String messageTime) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageTime = messageTime;
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return messageText + " " + messageUser + " " + messageTime;
    }
}
