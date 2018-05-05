package com.example.gebruiker.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;


public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;
    private static final String entries = "entries";
    private SQLiteDatabase db;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create table.
        String SQLdb = "CREATE TABLE entries (\n"
                + "	_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " title TEXT, \n"
                + "	content TEXT,\n"
                + "	mood TEXT,\n"
                + " timestamp DATETIME default current_timestamp\n"
                + ");";

        db.execSQL(SQLdb);

        // Sample entries.
        String sample1 = "INSERT INTO entries"
                + "(title, content, mood) " + "VALUES"
                + "('Nieuwe dag','Zonnig en 24 graden','Vrolijk')";

        String sample2 = "INSERT INTO entries"
                + "(title, content, mood) " + "VALUES"
                + "('Bevrijdingsdag','Vandaag naar Haarlem','Feestig')";

        db.execSQL(sample1);
        db.execSQL(sample2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drops database.
        db.execSQL("DROP TABLE IF EXISTS entries");

        // Create new database.
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        // Return instance of database.
        if(instance == null) {
            instance = new EntryDatabase(context, entries, null, 1);
        }
        return instance;
    }

    public Cursor selectAll() {
        db = getWritableDatabase();

        Cursor all = db.rawQuery("SELECT * FROM entries", null);
        return all;
    }

    public void insert(JournalEntry entry) {

        // Opens database connection.
        selectAll();

        // set the content.
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.getTitle());
        contentValues.put("content", entry.getContent());
        contentValues.put("mood", entry.getMood());

        db.insert(entries, null, contentValues);
    }

    public void delete(long id){

//        db = getWritableDatabase();

        // delete row from table
        String query = "DELETE from entries WHERE _id = " + id + ";";
        db.execSQL(query);

    }


}
