package com.example.bookshopservice;

import com.example.bookshopservice.dto.AuthorDto;
import com.example.bookshopservice.dto.BookDto;
import com.example.bookshopservice.dto.PublisherDto;
import com.example.bookshopservice.repository.*;
import com.example.bookshopservice.service.AuthorService;
import com.example.bookshopservice.service.BookService;
import com.example.bookshopservice.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    @GetMapping(path = "/books")
    public @ResponseBody Iterable<BookDto> getAllBooks()
    {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookRepository.findAll().forEach(book -> bookDtoList.add(new BookDto(book)));

        /*
        // Snippet of code below does the same.
        StreamSupport.
                stream(bookRepository.findAll().spliterator(), false).
                collect(Collectors.toList()).
                stream().forEach(book -> bookDtoList.add(new BookDto(book)));
         */
        return bookDtoList;
    }

    @GetMapping(path = "/authors")
    public @ResponseBody Iterable<AuthorDto> getAllAuthors()
    {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        authorRepository.findAll().forEach(author -> authorDtoList.add(new AuthorDto(author)));
        return authorDtoList;
    }

    @GetMapping(path = "/publishers")
    public @ResponseBody Iterable<PublisherDto> getAllPublishers()
    {
        List<PublisherDto> publisherDtoList = new ArrayList<>();
        publisherRepository.findAll().forEach((publisher -> publisherDtoList.add(new PublisherDto(publisher))));
        return publisherDtoList;
    }

    @GetMapping(path = "/books/author/{id}")
    public @ResponseBody Iterable<BookDto> getBooksByAuthor(@PathVariable int id){
        return bookService.getBooksByAuthor(id);
    }

    @GetMapping(path = "/books/author")
    public @ResponseBody Iterable<BookDto> getBooksByAuthor(@RequestParam String name){
        return bookService.getBooksByAuthor(name);
    }

    @GetMapping(path = "/books/publisher/{id}")
    public @ResponseBody Iterable<BookDto> getBooksByPublisher(@PathVariable int id){
        return bookService.getBooksByPublisher(id);
    }

    @GetMapping(path = "/books/publisher")
    public @ResponseBody Iterable<BookDto> getBooksByPublisher(@RequestParam String name){
        return bookService.getBooksByPublisher(name);
    }

    @DeleteMapping(path = "/book/{id}")
    public @ResponseBody String deleteBook(@PathVariable int id)
    {
        bookService.deleteBook(id);
        return "OK";
    }

    @DeleteMapping(path = "/book")
    public @ResponseBody String deleteBook(@RequestParam String name)
    {
        bookService.deleteBook(name);
        return "OK";
    }

    @DeleteMapping(path = "/author/{id}")
    public @ResponseBody String deleteAuthor(@PathVariable int id)
    {
        authorService.deleteAuthor(id);
        return "OK";
    }

    @DeleteMapping(path = "/author")
    public @ResponseBody String deleteAuthor(@RequestParam String name)
    {
        authorService.deleteAuthor(name);
        return "OK";
    }

    @DeleteMapping(path = "/publisher/{id}")
    public @ResponseBody String deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
        return "OK";
    }

    @DeleteMapping(path = "/publisher")
    public @ResponseBody String deletePublisher(@RequestParam String name) {
        publisherService.deletePublisher(name);
        return "OK";
    }

    @PutMapping(path= "/book/{id}/author")
    public @ResponseBody String changeAuthor(@PathVariable int id,
                                             @RequestParam String authorName) {
        bookService.changeAuthor(id, authorName);
        return "OK";
    }

    @PutMapping(path= "/book/author")
    public @ResponseBody String changeAuthor(@RequestParam String bookName,
                                             @RequestParam String authorName) {
        bookService.changeAuthor(bookName, authorName);
        return "OK";
    }

    @PutMapping(path= "/book/{id}/publisher")
    public @ResponseBody String changePublisher(@PathVariable int id,
                                             @RequestParam String publisherName) {
        bookService.changePublisher(id, publisherName);
        return "OK";
    }

    @PutMapping(path= "/book/publisher")
    public @ResponseBody String changePublisher(@RequestParam String bookName,
                                             @RequestParam String publisherName) {
        bookService.changePublisher(bookName, publisherName);
        return "OK";
    }
}
