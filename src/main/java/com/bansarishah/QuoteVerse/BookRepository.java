package com.bansarishah.QuoteVerse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Method to find a book by its title and author's name
    Optional<Book> findByTitleIgnoreCaseAndAuthorNameIgnoreCase(String title, String authorName);
}
