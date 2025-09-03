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

    // This method handles GET requests to the homepage ("/")
    @GetMapping("/")
    public String showQuotesPage(Model model) {
        // 1. Get all quotes from the database
        model.addAttribute("quotes", quoteRepository.findAll());
        // 2. Prepare a new empty quote object for the form
        model.addAttribute("newQuote", new Quote());
        // 3. Return the name of the HTML file to display (index.html)

        return "index";
    }

    // This method handles POST requests from the form on the homepage
    @PostMapping("/addquote")
    public String addQuote(@ModelAttribute Quote newQuote) {
        // Save the new quote object (from the form) to the database
        quoteRepository.save(newQuote);
        // Redirect the user back to the homepage to see the new quote

        return "redirect:/";
    }
}
