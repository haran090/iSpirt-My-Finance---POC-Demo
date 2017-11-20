package com.haran.myfinapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SuccessAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_acitivty);
        (findViewById(R.id.success_tick)).animate().scaleY(1).scaleX(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SuccessAcitivty.this, RequestConsentActivity.class));
                finish();
            }
        }, 3000);
        NetworkUtil.writeToLogger("My Fin App", "Account Aggregator Linked");
    }
}
