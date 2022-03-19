package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Publisher;

import java.util.Set;

public class PublisherDto {
    private String name;

    private Set<String> books;

    public PublisherDto(Publisher publisher) {
        this.name = publisher.getName();
        publisher.getBooks().forEach(book -> books.add(book.getName()));
    }

    public Set<String> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
