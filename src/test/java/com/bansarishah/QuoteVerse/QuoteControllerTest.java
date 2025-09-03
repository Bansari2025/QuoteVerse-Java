package com.bansarishah.QuoteVerse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteControllerTest {

    // @Mock creates a "stunt double" for the QuoteRepository.
    // It's a fake object that we can control. It will NOT touch the real database.
    @Mock
    private QuoteRepository quoteRepository;

    // @InjectMocks creates a real instance of QuoteController, but it will
    // automatically inject any fields marked with @Mock (like our quoteRepository) into it.
    @InjectMocks
    private QuoteController quoteController;

    @Mock
    private Model model;

    // The @BeforeEach annotation makes this method run before EACH @Test method.
    // It's a great place for common setup code.

    @Test
    void showQuotesPage_shouldReturnIndexViewWithQuotes() {
        // --- ARRANGE ---
        // 1. Create some fake data to be returned by our mock repository.
        Quote quote1 = new Quote();
        quote1.setText("Test Quote 1");
        Quote quote2 = new Quote();
        quote2.setText("Test Quote 2");
        List<Quote> fakeQuotes = List.of(quote1, quote2);

        // 2. Define the behavior of our mock.
        // "WHEN the findAll() method is called on quoteRepository, THEN return our fakeQuotes list."
        when(quoteRepository.findAll()).thenReturn(fakeQuotes);

        // --- ACT ---
        // 3. Call the actual method we want to test.
        String viewName = quoteController.showQuotesPage(model);

        // --- ASSERT ---
        // 4. Check if the results are what we expect.
        // Did the method return the correct view name?
        assertEquals("index", viewName, "The view name should be 'index'");

        // Did the method add the list of quotes to the model?
        verify(model, times(1)).addAttribute("quotes", fakeQuotes);

        // Is the list of quotes in the model the same one we faked?
        verify(model, times(1)).addAttribute(eq("newQuote"), any(Quote.class));


    }

    @Test
    void addQuote_shouldSaveQuoteAndRedirect() {
        // --- ARRANGE ---
        // 1. Create a fake Quote object, representing what a user would submit in the form.
        Quote newQuote = new Quote();
        newQuote.setText("A new test quote.");
        newQuote.setBookTitle("Test Book");

        // --- ACT ---
        // 2. Call the method we want to test.
        String redirectUrl = quoteController.addQuote(newQuote);

        // --- ASSERT ---
        // 3. Verify the results.
        // Did the method return the correct redirect string?
        assertEquals("redirect:/", redirectUrl, "The return value should be a redirect to the homepage");

        // This is a powerful Mockito feature:
        // "VERIFY that the save() method of our quoteRepository was called EXACTLY 1 time
        // with the newQuote object we created."
        // This proves our controller's logic correctly called the repository to save the data.

        verify(quoteRepository, times(1)).save(newQuote);

    }

}