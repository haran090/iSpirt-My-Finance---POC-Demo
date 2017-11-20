package com.haran.loggermyfin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private TextView textView;
    private ScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_log);
        scrollview = (ScrollView) findViewById(R.id.scroll_view);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("logs");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(textView != null){
                    if(dataSnapshot.getValue() instanceof HashMap){
                        HashMap hashMap = (HashMap)dataSnapshot.getValue();
                        for(Object key : hashMap.keySet()){
                            if(key instanceof String){
                                String html_string = "<br><b>" + key + " </b><br>";
                                textView.append(Html.fromHtml(html_string));
                                textView.append(hashMap.get(key).toString());
                                textView.append(Html.fromHtml("<br><br>"));

                                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        }
                    }
                    else{
                        textView.append("\n" + dataSnapshot.getKey() + " : " + dataSnapshot.getValue() + "\n");
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            if(myRef != null){
                myRef.removeValue();
                textView.setText("");
                Toast.makeText(this, "Cleared Log", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
