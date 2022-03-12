package com.example.bookshopservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @PostMapping(path = "/add_book")
    public @ResponseBody String addBook(@RequestParam String bookName,
                                        @RequestParam String authorName,
                                        @RequestParam String publisherName)
    {
        Book nb = new Book();
        nb.setName(bookName);
        Author author = authorRepository.findAuthorByName(authorName);
        nb.setAuthorId(author);
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        nb.setPublisherId(publisher);
        String template = "Book: %s; Author: %s; Publisher: %s;";
        String aName = author.getName();
        String pName = publisher.getName();
        String message = String.format(template, bookName, aName, pName);
        return message;
    }

    @PostMapping(path = "/add_author")
    public @ResponseBody String addAuthor(@RequestParam String authorName)
    {
        // @ResponseBody means the returned String is the response, not a view name.
        // @RequestParam means it is a parameter from the GET or POST request.
        Author na = new Author();
        na.setName(authorName);
        return "Author added.";
    }

    @PostMapping(path = "/add_publisher")
    public @ResponseBody String addPublisher(@RequestParam String publisherName)
    {
        // @ResponseBody means the returned String is the response, not a view name.
        // @RequestParam means it is a parameter from the GET or POST request.
        Publisher np = new Publisher();
        np.setName(publisherName);
        return "Author added.";
    }

    @GetMapping(path = "/getAllBooks")
    public @ResponseBody Iterable<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    @GetMapping(path = "/getAllAuthors")
    public @ResponseBody Iterable<Author> getAllAuthors()
    {
        return authorRepository.findAll();
    }

    @GetMapping(path = "/getAllPublishers")
    public @ResponseBody Iterable<Publisher> getAllPublishers()
    {
        return publisherRepository.findAll();
    }

}
