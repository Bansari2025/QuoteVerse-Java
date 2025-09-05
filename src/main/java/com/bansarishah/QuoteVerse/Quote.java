package com.bansarishah.QuoteVerse;

import jakarta.persistence.*;

@Entity// Tells JPA this class is a database table
public class Quote {

    @Id// Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Auto-generates the ID

    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    /*private String author;
    private String bookTitle;*/

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Getters and Setters... (Your IDE can generate these for you)
    // Right-click -> Generate -> Getters and Setters -> Select all fields

    public Quote(){
    }

//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }

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

//    public String getBookTitle() {
//        return bookTitle;
//    }
//
//    public void setBookTitle(String bookTitle) {
//        this.bookTitle = bookTitle;
//    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
