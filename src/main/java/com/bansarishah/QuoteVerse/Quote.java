package com.bansarishah.QuoteVerse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity// Tells JPA this class is a database table
public class Quote {

    @Id// Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Auto-generates the ID

    private Long id;
    private String text;
    private String bookTitle;

    // Getters and Setters... (Your IDE can generate these for you)
    // Right-click -> Generate -> Getters and Setters -> Select all fields

    public Quote(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
