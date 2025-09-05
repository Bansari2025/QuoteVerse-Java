package com.bansarishah.QuoteVerse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Method to find an author by name, ignoring case
    Optional<Author> findByNameIgnoreCase(String name);
}
