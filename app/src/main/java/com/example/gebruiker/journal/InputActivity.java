package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry (View view) {

        EntryDatabase db = EntryDatabase.getInstance(this);

        // Get data from textviews.
        TextView titleview = findViewById(R.id.titleInput);
        String title = titleview.getText().toString();

        TextView contentView = findViewById(R.id.contentInput);
        String content = contentView.getText().toString();

        TextView moodView = findViewById(R.id.moodInput);
        String mood = moodView.getText().toString();

        // Create new entry with text entered by user.
        JournalEntry entry = new JournalEntry(title, content, mood);

        // Insert entry in database.
        db.insert(entry);

        // After subimtted go back to main.
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
