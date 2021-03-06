package com.example.bookshopservice.service;

import com.example.bookshopservice.exception.NotFoundException;
import com.example.bookshopservice.exception.ObjectExistsException;
import com.example.bookshopservice.repository.Author;
import com.example.bookshopservice.repository.AuthorRepository;
import com.example.bookshopservice.repository.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void addAuthor(String name) {
        Author na = authorRepository.findAuthorByName(name);
        if (na != null) {
            throw new ObjectExistsException("Author already exists!");
        }
        na = new Author();
        na.setName(name);
        authorRepository.save(na);
    }

    public void deleteAuthor(int id) {
        Author author = authorRepository.findAuthorById(id);
        if (author == null) {
            throw new NotFoundException("Author not found!");
        }
        Iterable<Book> books = author.getBooks();
        for (Book b : books) {
            b.setAuthor(null);
        }
        authorRepository.delete(author);
    }

    public void deleteAuthor(String name) {
        Author author = authorRepository.findAuthorByName(name);
        if (author == null) {
            throw new NotFoundException("Author not found!");
        }
        Iterable<Book> books = author.getBooks();
        for (Book b : books) {
            b.setAuthor(null);
        }
        authorRepository.delete(author);
    }

}
