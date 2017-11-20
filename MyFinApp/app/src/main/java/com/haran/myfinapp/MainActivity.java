package com.haran.myfinapp;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("FinApp");
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.textview_main)).setText(Html.fromHtml("Effortlessly view and manage your <b>Finances</b> in one place"));
    }


    public void connectAggregator(View view) {
        BottomSheetDialogFragment bottomSheetDialogFragment = new EnterMobileBottomSheet();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), "Mobile Entry");
    }
}
