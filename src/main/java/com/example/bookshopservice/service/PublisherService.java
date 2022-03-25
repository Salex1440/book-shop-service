package com.example.bookshopservice.service;

import com.example.bookshopservice.exception.NotFoundException;
import com.example.bookshopservice.exception.ObjectExistsException;
import com.example.bookshopservice.repository.Book;
import com.example.bookshopservice.repository.BookRepository;
import com.example.bookshopservice.repository.Publisher;
import com.example.bookshopservice.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    public void addPublisher(String name) {
        Publisher np = publisherRepository.findPublisherByName(name);
        if (np != null) {
            throw new ObjectExistsException("Publisher already exists!");
        }
        np = new Publisher();
        np.setName(name);
        publisherRepository.save(np);
    }

    public void deletePublisher(int id) {
        Publisher publisher= publisherRepository.findPublisherById(id);
        if (publisher == null) {
            throw new NotFoundException("Publisher not found!");
        }
        Iterable<Book> books = publisher.getBooks();
        for (Book b : books) {
            b.setPublisher(null);
        }
        publisherRepository.delete(publisher);
    }

    public void deletePublisher(String name) {
        Publisher publisher= publisherRepository.findPublisherByName(name);
        if (publisher == null) {
            throw new NotFoundException("Publisher not found!");
        }
        Iterable<Book> books = publisher.getBooks();
        for (Book b : books) {
            b.setPublisher(null);
        }
        publisherRepository.delete(publisher);
    }
}
