package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Author;
import com.example.bookshopservice.repository.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto {

    private String name;

    private List<String> books;

    public AuthorDto(Author author) {
        this.name = author.getName();
        books = new ArrayList<>();
        author.getBooks().forEach(book -> books.add(book.getName()));
    }

    public Iterable<String> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
