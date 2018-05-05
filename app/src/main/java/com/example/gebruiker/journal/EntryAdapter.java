package com.example.gebruiker.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    // Constructor.
    public EntryAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, R.layout.entry_row, cursor, flags);
    }

    public void bindView(View view, Context context, Cursor cursor) {

        // Retrieve title and show it in mains listview.
        int colIndex =  cursor.getColumnIndex("title");
        String titleCursor = cursor.getString(colIndex);
        TextView title = view.findViewById(R.id.title);
        title.setText(titleCursor);

        //  Retrieve mood and show it in mains listview.
        int moodIndex =  cursor.getColumnIndex("mood");
        String moodCursor = cursor.getString(moodIndex);
        TextView mood = view.findViewById(R.id.mood);
        mood.setText(moodCursor);

        //  Retrieve date and show it in mains listview.
        int dateIndex =  cursor.getColumnIndex("timestamp");
        String dateCursor = cursor.getString(dateIndex);
        TextView date = view.findViewById(R.id.date);
        date.setText(dateCursor);
    }
}

