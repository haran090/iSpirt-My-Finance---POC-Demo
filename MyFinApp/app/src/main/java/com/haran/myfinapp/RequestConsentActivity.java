package com.haran.myfinapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by haran on 17-Nov-17.
 */

public class RequestConsentActivity extends AppCompatActivity{

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_consent_sheet);
    }

    public void shareData(View view) {
        mWebview = ((WebView)findViewById(R.id.webview));
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl("http://54.251.140.175/request_consent.php");
        mWebview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
//                Intent intent = new Intent("com.haran.start.aa");
//                sendBroadcast(intent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NetworkUtil.makeOkHttpRequest("Fin App", "Request Consent");
                    }
                }).start();
            }
        });
    }
}
