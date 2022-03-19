package com.example.bookshopservice;

import com.example.bookshopservice.dto.AuthorDto;
import com.example.bookshopservice.dto.BookDto;
import com.example.bookshopservice.dto.PublisherDto;
import com.example.bookshopservice.repository.*;
import com.example.bookshopservice.service.AuthorService;
import com.example.bookshopservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "/bookshop")
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

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
        nb.setAuthor(author);
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        nb.setPublisher(publisher);
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
        return String.format(template, bookName, aName, pName);
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
    public @ResponseBody Iterable<BookDto> getAllBooks()
    {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookRepository.findAll().forEach(book -> bookDtoList.add(new BookDto(book)));
        return bookDtoList;
    }

    @GetMapping(path = "/get_all_authors")
    public @ResponseBody Iterable<AuthorDto> getAllAuthors()
    {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        authorRepository.findAll().forEach(author -> authorDtoList.add(new AuthorDto(author)));
        return authorDtoList;
    }

    @GetMapping(path = "/get_all_publishers")
    public @ResponseBody Iterable<PublisherDto> getAllPublishers()
    {
        List<PublisherDto> publisherDtoList = new ArrayList<>();
        publisherRepository.findAll().forEach((publisher -> publisherDtoList.add(new PublisherDto(publisher))));
        return publisherDtoList;
    }

    @GetMapping(path = "/get_books_by_author")
    public @ResponseBody Iterable<String> getBooksByAuthor(@RequestParam String name){
        List<String> names = new ArrayList<>();
        Author author = authorRepository.findAuthorByName(name);
        if (author == null) {
            return names;
        }
        Iterable<Book> books = author.getBooks();
        for (Book b : books) {
            names.add(b.getName());
        }
        return names;
    }

    @GetMapping(path = "/get_books_by_publisher")
    public @ResponseBody Iterable<String> getBooksByPublisher(@RequestParam String name){
        List<String> names = new ArrayList<>();
        Publisher publisher = publisherRepository.findPublisherByName(name);
        if (publisher == null) {
            return names;
        }
        Iterable<Book> books = publisher.getBooks();
        for (Book b : books) {
            names.add(b.getName());
        }

        return names;
    }

    @DeleteMapping(path = "/delete_book")
    public @ResponseBody String deleteBook(@RequestParam String name)
    {
        bookService.deleteBook(name);
        return "OK";
    }

    @DeleteMapping(path = "/delete_author")
    public @ResponseBody String deleteAuthor(@RequestParam String name)
    {
        authorService.deleteAuthor(name);
        return "OK";
    }

    @DeleteMapping(path = "/delete_publisher")
    public @ResponseBody String deletePublisher(@RequestParam String name) {
        Publisher publisher= publisherRepository.findPublisherByName(name);
        if (publisher == null) {
            return String.format("Publisher %s doesn't exist!", name);
        }
        Iterable<Book> books = publisher.getBooks();
        for (Book b : books) {
            b.setPublisher(null);
        }
        publisherRepository.delete(publisher);
        return String.format("Publisher \"%s\" was deleted.", name);
    }

    @PutMapping(path= "/change_author")
    public @ResponseBody String changeAuthor(@RequestParam String bookName,
                                             @RequestParam String authorName) {
        bookService.changeAuthor(bookName, authorName);
        return "OK";
    }

    @PutMapping(path= "/change_publisher")
    public @ResponseBody String changePublisher(@RequestParam String bookName,
                                             @RequestParam String publisherName) {
        bookService.changePublisher(bookName, publisherName);
        return "OK";
    }
}
