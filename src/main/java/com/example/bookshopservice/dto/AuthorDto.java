package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Author;

import java.util.Set;

public class AuthorDto {

    private String name;

    private Set<String> books;

    public AuthorDto(Author author) {
        this.name = author.getName();
        author.getBooks().forEach(book -> books.add(book.getName()));
    }

    public Set<String> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
