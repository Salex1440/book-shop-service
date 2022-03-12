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
        String template = "Book: %s; Author: %s; Publisher: %s;";
        String message = String.format(template, bookName, aName, pName);
        return message;
    }

    @PostMapping(path = "/add_author")
    public @ResponseBody String addAuthor(@RequestParam String authorName)
    {
        // @ResponseBody means the returned String is the response, not a view name.
        // @RequestParam means it is a parameter from the GET or POST request.
        Author na = authorRepository.findAuthorByName(authorName);
        if (na != null) {
            return String.format("Author \"%s\" exists!", authorName);
        }
        na = new Author();
        na.setName(authorName);
        authorRepository.save(na);
        return String.format("Author %s added.", authorName);
    }

    @PostMapping(path = "/add_publisher")
    public @ResponseBody String addPublisher(@RequestParam String publisherName)
    {
        // @ResponseBody means the returned String is the response, not a view name.
        // @RequestParam means it is a parameter from the GET or POST request.
        Publisher np = publisherRepository.findPublisherByName(publisherName);
        if (np != null) {
            return String.format("Publiser \"%s\" exists!", publisherName);
        }
        np = new Publisher();
        np.setName(publisherName);
        publisherRepository.save(np);
        return String.format("Publisher \"%s\" added.", publisherName);
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
            return String.format("Book %s doesn't exist!", name);
        }
        bookRepository.delete(book);
        return String.format("Book \"%s\" was deleted.", name);
    }

    @DeleteMapping(path = "/delete_author")
    public @ResponseBody String deleteAuthor(@RequestParam String name)
    {
        Author author = authorRepository.findAuthorByName(name);
        if (author == null) {
            return String.format("Author %s doesn't exist!", name);
        }
        authorRepository.delete(author);
        return String.format("Author \"%s\" was deleted.", name);
    }

    @DeleteMapping(path = "/delete_publisher")
    public @ResponseBody String deletePublisher(@RequestParam String name) {
        Publisher publisher= publisherRepository.findPublisherByName(name);
        if (publisher == null) {
            return String.format("Publisher %s doesn't exist!", name);
        }
        publisherRepository.delete(publisher);
        return String.format("Publisher \"%s\" was deleted.", name);
    }

    @PutMapping(path= "/change_author")
    public @ResponseBody String changeAuthor(@RequestParam String bookName,
                                             @RequestParam String authorName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            return String.format("There is no book with this name: %s.", bookName);
        }
        Author author = authorRepository.findAuthorByName(authorName);
        if (author == null) {
            return String.format("There is no author with this name: %s", authorName);
        }
        Author prevAuthor = book.getAuthorId();
        book.setAuthorId(author);
        bookRepository.save(book);
        return String.format("Author of book \"%s\" was changed from %s to %s.",
                bookName, prevAuthor.getName(), author.getName());
    }

    @PutMapping(path= "/change_publisher")
    public @ResponseBody String changePublisher(@RequestParam String bookName,
                                             @RequestParam String publisherName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            return String.format("There is no book with this name: %s.", bookName);
        }
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        if (publisher == null) {
            return String.format("There is no publisher with this name: %s", publisherName);
        }
        Publisher prevPublisher = book.getPublisherId();
        book.setPublisherId(publisher);
        bookRepository.save(book);
        return String.format("Publisher of book \"%s\" was changed from %s to %s.",
                bookName, prevPublisher.getName(), publisher.getName());
    }
}
