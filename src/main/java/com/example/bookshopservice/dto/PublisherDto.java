package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Publisher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PublisherDto {

    private int id;

    private String name;

    private List<String> books;

    public PublisherDto(Publisher publisher) {
        id = publisher.getId();
        name = publisher.getName();
        books = new ArrayList<>();
        publisher.getBooks().forEach(book -> books.add(book.getName()));
    }
}
