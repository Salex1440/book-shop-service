package com.example.bookshopservice;

import com.example.bookshopservice.dto.AuthorDto;
import com.example.bookshopservice.dto.BookDto;
import com.example.bookshopservice.dto.PublisherDto;
import com.example.bookshopservice.exception.ObjectExistsException;
import com.example.bookshopservice.repository.*;
import com.example.bookshopservice.service.AuthorService;
import com.example.bookshopservice.service.BookService;
import com.example.bookshopservice.service.PublisherService;
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
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @PostMapping(path = "/add_book")
    public @ResponseBody String addBook(@RequestParam String bookName,
                                        @RequestParam String authorName,
                                        @RequestParam String publisherName)
    {
        bookService.addBook(bookName, authorName, publisherName);
        return "OK";
    }

    @PostMapping(path = "/add_author")
    public @ResponseBody String addAuthor(@RequestParam String authorName)
    {
        authorService.addAuthor(authorName);
        return "OK";
    }

    @PostMapping(path = "/add_publisher")
    public @ResponseBody String addPublisher(@RequestParam String publisherName)
    {
        publisherService.addPublisher(publisherName);
        return "OK";
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
    public @ResponseBody Iterable<BookDto> getBooksByAuthor(@RequestParam String name){
        return bookService.getBooksByAuthor(name);
    }

    @GetMapping(path = "/get_books_by_publisher")
    public @ResponseBody Iterable<BookDto> getBooksByPublisher(@RequestParam String name){
        return bookService.getBooksByPublisher(name);
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
        publisherService.deletePublisher(name);
        return "OK";
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
