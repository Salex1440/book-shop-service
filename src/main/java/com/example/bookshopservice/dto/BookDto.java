package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Book;
import lombok.Getter;

import java.util.List;

@Getter
public class BookDto {

    private int id;

    private String name;

    private String author;

    private String publisher;

    // Data transfer object.
    public BookDto(Book book) {
        id = book.getId();
        name = book.getName();
        // TODO: Is there another way to check variables for nullable state?
        if (author == null) {
            author = "null";
        } else {
            author = book.getAuthor().getName();
        }
        if (publisher == null) {
            publisher = "null";
        } else {
            publisher = book.getPublisher().getName();
        }
    }
}
