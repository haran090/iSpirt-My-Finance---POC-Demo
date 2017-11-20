package com.haran.myfinapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FetchDataActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }
        }, 3000);
    }

    public void load() {
        mWebview = ((WebView)findViewById(R.id.webview));
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl("http://54.251.140.175/fetch_docs.php?id=4875");
        mWebview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                startActivity(new Intent(FetchDataActivity.this, DataDisplayActivity.class));
                finish();
            }
        });
    }
}
