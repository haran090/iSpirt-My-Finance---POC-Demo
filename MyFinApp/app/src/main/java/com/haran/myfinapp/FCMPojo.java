package com.haran.myfinapp;

/**
 * Created by haran on 17-Nov-17.
 */

public class FCMPojo {

    String to;
    Message data;

    public static class Message {
        String title;
        String message;

        public Message(String title, String message) {
            this.title = title;
            this.message = message;
        }
    }

    public FCMPojo(String to, Message data) {
        this.to = to;
        this.data = data;
    }
}
