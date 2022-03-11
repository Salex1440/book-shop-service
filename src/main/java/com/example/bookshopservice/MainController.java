package com.example.bookshopservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/books")
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping(path = "/get")
    public @ResponseBody Iterable<Book> getAllBooks ()
    {
        // @ResponseBody means the returned String is the response, not a view name.
        // @RequestParam means it is a parameter from the GET or POST request.
        return bookRepository.findAll();
    }



}
