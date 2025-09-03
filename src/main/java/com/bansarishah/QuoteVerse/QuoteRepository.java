package com.bansarishah.QuoteVerse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository// Marks this as a Spring Data repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    // We don't need to write any code here!
    // JpaRepository gives us findAll(), findById(), save(), delete(), etc. for free.
}
