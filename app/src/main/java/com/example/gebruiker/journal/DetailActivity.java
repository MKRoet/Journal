package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get intent and bundle.
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        // Get textviews.
        TextView title = findViewById(R.id.detailTitle);
        TextView timestamp = findViewById(R.id.detailDate);
        TextView content = findViewById(R.id.detailContent);
        TextView mood = findViewById(R.id.detailMood);

        // Updates textview with text from bundle.
        title.setText(bundle.getString("title"));
        timestamp.setText(bundle.getString("timestamp"));
        content.setText(bundle.getString("content"));
        mood.setText(bundle.getString("mood"));
    }
}
