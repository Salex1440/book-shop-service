package com.example.bookshopservice.service;

import com.example.bookshopservice.exception.NotFoundException;
import com.example.bookshopservice.repository.Book;
import com.example.bookshopservice.repository.BookRepository;
import com.example.bookshopservice.repository.Publisher;
import com.example.bookshopservice.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

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
        Publisher prevPublisher = book.getPublisher();
        String prevPublisherName;
        if (prevPublisher == null) {
            prevPublisherName = "null";
        } else {
            prevPublisherName = prevPublisher.getName();
        }
        book.setPublisher(publisher);
        Book nbook = bookRepository.save(book);

        return Pair.of(nbook, book);
    }
}
