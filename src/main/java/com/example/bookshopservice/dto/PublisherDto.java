package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherDto {
    private String name;

    private List<String> books;

    public PublisherDto(Publisher publisher) {
        this.name = publisher.getName();
        books = new ArrayList<>();
        publisher.getBooks().forEach(book -> books.add(book.getName()));
    }

    public Iterable<String> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
