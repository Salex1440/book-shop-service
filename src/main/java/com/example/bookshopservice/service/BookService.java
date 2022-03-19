package com.example.bookshopservice.service;

import com.example.bookshopservice.exception.NotFoundException;
import com.example.bookshopservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

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
    }

    public void deleteBook(String bookName) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new NotFoundException("Book not found!");
        }
        bookRepository.delete(book);
    }

}
