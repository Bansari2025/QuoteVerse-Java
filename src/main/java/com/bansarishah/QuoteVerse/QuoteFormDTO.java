package com.bansarishah.QuoteVerse;

// A simple class to hold the data from our HTML form
public class QuoteFormDTO {

    private String text;
    private String authorName;
    private String bookTitle;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
