package com.example.gebruiker.journal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void SubmitClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addEntry (View view) {


        EntryDatabase db = EntryDatabase.getInstance(this);

        // gets the title, content and mood from the corresponding text views in input_activity.xml
        String title = ((TextView) findViewById(R.id.titleInput)).getText().toString();
        String content = ((TextView) findViewById(R.id.contentInput)).getText().toString();
        String mood = ((TextView) findViewById(R.id.moodInput)).getText().toString();

        // Creates a new entry and fills it with the content entered by the user
        JournalEntry entry = new JournalEntry(title, content, mood);

        // Calls the insert function to insert the entry in the database
        db.insert(entry);

        // Goes back to the mainactivity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
