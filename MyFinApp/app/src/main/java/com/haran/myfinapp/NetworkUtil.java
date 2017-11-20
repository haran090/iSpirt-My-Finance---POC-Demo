package com.haran.myfinapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by haran on 17-Nov-17.
 */

public class NetworkUtil {

    public static void makeOkHttpRequest(String title, String message) {
        OkHttpClient client = new OkHttpClient();
        String json = new Gson().toJson(new FCMPojo("/topics/AAApp", new FCMPojo.Message(title, message)), FCMPojo.class);


        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "key=AIzaSyDjJJ2hbeS2emI0_tgIP96alwwUO5FgZIw")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToLogger(String title, String data) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("logs");
        DatabaseReference new_data = myRef.push();
        DatabaseReference new_data_value = new_data.child(title);
        new_data_value.setValue(data);
    }
}
