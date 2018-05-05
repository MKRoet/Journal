package com.example.gebruiker.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        ListView mainList = findViewById(R.id.MainList);
        EntryAdapter adapter = new EntryAdapter(this, db.selectAll());
        mainList.setAdapter(adapter);

    }

    public void onFloatingClicked(View view) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }



    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // Remember position
            int position = i;

            // Add journalentry.
            Cursor clicked = (Cursor) adapterView.getItemAtPosition(i);
            String title = clicked.getString(clicked.getColumnIndex("title"));
            String content = clicked.getString(clicked.getColumnIndex("content"));
            String mood = clicked.getString(clicked.getColumnIndex("mood"));
            String timestamp = clicked.getString(clicked.getColumnIndex("timestamp"));
            JournalEntry clickedItem = new JournalEntry(title, content, mood);

            // pass information to the next activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clicked_item", clickedItem);
            startActivity(intent);

        }
    }

    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get the id of the current item
            Cursor clickedItem = (Cursor) adapterView.getItemAtPosition(i);
            long id = clickedItem.getInt(clickedItem.getColumnIndex("_id"));

            // delete the item & update the main activity
            db.delete(id);
            updateData();
            Toast.makeText(MainActivity.this, "The selected item is deleted.", Toast.LENGTH_SHORT).show();

            // event is handled with
            return true;
        }
        }
    // make sure interface is up to date
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }


}
