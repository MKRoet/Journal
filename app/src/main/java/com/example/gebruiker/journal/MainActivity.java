package com.example.gebruiker.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get database.
        db = EntryDatabase.getInstance(getApplicationContext());

        // Gets cursor with all entries.
        Cursor cursor = db.selectAll();

        // Get mainlist and set adapter.
        ListView mainList = findViewById(R.id.MainList);
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor, 1);
        mainList.setAdapter(adapter);

        // Set onitemlongclick listener.
        AdapterView.OnItemLongClickListener clickedlong = new ListItemLongClickListener();
        mainList.setOnItemLongClickListener(clickedlong);

        // Set onitemclick listener.
        AdapterView.OnItemClickListener clicked = new ListItemClickListener();
        mainList.setOnItemClickListener(clicked);

    }

    public void onFloatingClicked(View view) {

        // If button clicked go to next event.
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {

            // Get cursor.
            Cursor clicked = (Cursor) adapter.getItemAtPosition(i);

            // Get column.
            int titleColumn = clicked.getColumnIndex("title");
            int titleContent = clicked.getColumnIndex("content");
            int titleMood = clicked.getColumnIndex("mood");
            int titleTimestamp = clicked.getColumnIndex("timestamp");

            // Get string in column.
            String title = clicked.getString(titleColumn);
            String content = clicked.getString(titleContent);
            String mood = clicked.getString(titleMood);
            String timestamp = clicked.getString(titleTimestamp);

            // Create bundle and insert data.
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("content", content);
            bundle.putString("mood", mood);
            bundle.putString("timestamp", timestamp);

            // Start next activity with appropriate data when clicked.
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    }

    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapter, View view, int i, long l) {

            // Get position of clicked item.
            Cursor clickedItem = (Cursor) adapter.getItemAtPosition(i);

            // Get column id of clicked item and convert it to a long.
            int temp = clickedItem.getColumnIndex("_id");
            int tempId = clickedItem.getInt(temp);
            long id = Long.valueOf(tempId);

            // Delete item.
            db.delete(id);

            // Update mainlist.
            updateData();

            return true;
        }
    }

    private void updateData() {

        // Update interface.
        Cursor update = db.selectAll();
        adapter.swapCursor(update);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}
