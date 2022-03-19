package com.example.bookshopservice.service;

import com.example.bookshopservice.dto.BookDto;
import com.example.bookshopservice.exception.NotFoundException;
import com.example.bookshopservice.exception.ObjectExistsException;
import com.example.bookshopservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public void addBook(String bookName, String authorName, String publisherName) {
        Book nb = bookRepository.findBookByName(bookName);
        if (nb != null) {
            throw new ObjectExistsException("Book already exists!");
        }
        nb = new Book();
        nb.setName(bookName);
        Author author = authorRepository.findAuthorByName(authorName);
        nb.setAuthor(author);
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        nb.setPublisher(publisher);
        bookRepository.save(nb);
    }

    public Iterable<BookDto> getBooksByAuthor(String name) {
        Author author = authorRepository.findAuthorByName(name);
        if (author == null) {
            return null;
        }
        List<BookDto> bookDtoList = new ArrayList<>();
        author.getBooks().forEach(book -> bookDtoList.add(new BookDto(book)));
        return bookDtoList;
    }

    public Iterable<BookDto> getBooksByPublisher(String name){
        Publisher publisher = publisherRepository.findPublisherByName(name);
        if (publisher == null) {
            return null;
        }
        List<BookDto> bookDtoList = new ArrayList<>();
        publisher.getBooks().forEach(book -> bookDtoList.add(new BookDto(book)));
        return bookDtoList;
    }

    public void changePublisher(String bookName, String publisherName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new NotFoundException("Book not found!");
        }
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        if (publisher == null) {
            throw new NotFoundException("Publisher not found!");
        }
        book.setPublisher(publisher);
        bookRepository.save(book);
    }

    public void changeAuthor(String bookName, String authorName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new NotFoundException("Book not found!");
        }
        Author author = authorRepository.findAuthorByName(authorName);
        if (author == null) {
            throw new NotFoundException("Author not found!");
        }
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public void deleteBook(String bookName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new NotFoundException("Book not found!");
        }
        bookRepository.delete(book);
    }

}
