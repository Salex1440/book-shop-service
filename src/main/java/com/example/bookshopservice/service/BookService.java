package com.example.bookshopservice.service;

import com.example.bookshopservice.exception.NotFoundException;
import com.example.bookshopservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public Pair<Book, Book> changePublisher(String bookName, String publisherName) {

        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new NotFoundException("Book not found!");
        }
        Publisher publisher = publisherRepository.findPublisherByName(publisherName);
        if (publisher == null) {
            throw new NotFoundException("Publisher not found!");
        }
        book.setPublisher(publisher);
        Book nbook = bookRepository.save(book);

        return Pair.of(nbook, book);
    }

    public Pair<Book, Book> changeAuthor(String bookName, String authorName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new NotFoundException("Book not found!");
        }
        Author author = authorRepository.findAuthorByName(authorName);
        if (author == null) {
            throw new NotFoundException("Author not found!");
        }
        book.setAuthor(author);
        Book nbook = bookRepository.save(book);
        return Pair.of(nbook, book);
    }

}
