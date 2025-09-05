package com.bansarishah.QuoteVerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Use @Controller for web pages with Thymeleaf
public class QuoteController {

    @Autowired// Spring automatically provides an instance of QuoteRepository
    private QuoteRepository quoteRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // This method handles GET requests to the homepage ("/")
    @GetMapping("/")
    public String showQuotesPage(Model model) {
        // 1. Get all quotes from the database
        model.addAttribute("quotes", quoteRepository.findAll());
        // 2. Prepare a new empty quote object for the form
        //model.addAttribute("newQuote", new Quote());
        // Use the new DTO for the form
        model.addAttribute("quoteFormDTO", new QuoteFormDTO());
        // 3. Return the name of the HTML file to display (index.html)

        return "index";
    }

    // This method handles POST requests from the form on the homepage
    @PostMapping("/addquote")
    public String addQuote(@ModelAttribute QuoteFormDTO quoteFormDTO) {
        // Save the new quote object (from the form) to the database
        //quoteRepository.save(newQuote);

        // 1. Find or create the Author
        Author author = authorRepository.findByNameIgnoreCase(quoteFormDTO.getAuthorName()).orElseGet(() -> {
            Author newAuthor = new Author();
            newAuthor.setName(quoteFormDTO.getAuthorName());
            return authorRepository.save(newAuthor);
        });

        // 2. Find or create the Book, linking it to the author
        Book book = bookRepository.findByTitleIgnoreCaseAndAuthorNameIgnoreCase(quoteFormDTO.getBookTitle(), author.getName()).orElseGet(() -> {
            Book newBook = new Book();
            newBook.setTitle(quoteFormDTO.getBookTitle());
            newBook.setAuthor(author);
            return bookRepository.save(newBook);
        });

        // 3. Create and save the new Quote
        Quote newQuote = new Quote();
        newQuote.setText(quoteFormDTO.getText());
        newQuote.setBook(book);
        quoteRepository.save(newQuote);

        // Redirect the user back to the homepage to see the new quote

        return "redirect:/";
    }
}
