package com.example.bookshopservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/bookshop")
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
        Book nb = bookRepository.findBookByName(bookName);
        if (nb != null) {
            return String.format("Book \"%s\" exists!", bookName);
        }
        nb = new Book();
        nb.setName(bookName);
        Author author = authorRepository.findAuthorByName(authorName);
        nb.setAuthorId(author);
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        nb.setPublisherId(publisher);
        String template = "Book: %s; Author: %s; Publisher: %s;";
        String aName, pName;
        if (author == null) {
            aName = "null";
        } else {
            aName = author.getName();
        }
        if (publisher == null) {
            pName = "null";
        } else {
            pName = publisher.getName();
        }
        bookRepository.save(nb);
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
        authorRepository.save(na);
        return "Author added.";
    }

    @PostMapping(path = "/add_publisher")
    public @ResponseBody String addPublisher(@RequestParam String publisherName)
    {
        // @ResponseBody means the returned String is the response, not a view name.
        // @RequestParam means it is a parameter from the GET or POST request.
        Publisher np = new Publisher();
        np.setName(publisherName);
        publisherRepository.save(np);
        return "Author added.";
    }

    @GetMapping(path = "/get_all_books")
    public @ResponseBody Iterable<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    @GetMapping(path = "/get_all_authors")
    public @ResponseBody Iterable<Author> getAllAuthors()
    {
        return authorRepository.findAll();
    }

    @GetMapping(path = "/get_all_publishers")
    public @ResponseBody Iterable<Publisher> getAllPublishers()
    {
        return publisherRepository.findAll();
    }

    @DeleteMapping(path = "/delete_book")
    public @ResponseBody String deleteBook(@RequestParam String name)
    {
        Book book = bookRepository.findBookByName(name);
        if (book == null) {
            return String.format("Book %s doesn't exist", name);
        }
        bookRepository.delete(book);
        return String.format("Book \"%s\" was deleted.", book.getName());
    }
}
