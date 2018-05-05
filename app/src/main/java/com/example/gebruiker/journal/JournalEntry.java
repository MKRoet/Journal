package com.example.gebruiker.journal;

import java.io.Serializable;
import java.sql.Timestamp;

public class JournalEntry implements Serializable {

    // Properties.
    public int id;
    public String title;
    public String content;
    public String mood;
    private Timestamp timestamp;

    // Constructor.
    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    // Getters.
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    //Setters.
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
