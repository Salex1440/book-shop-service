package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Book;

import java.util.List;

public class BookDto {

    private String name;

    private String author;

    // Data transfer object.
    public BookDto(Book book) {
        this.name = book.getName();
        this.author = book.getAuthor().getName();
    }

    public String getName() {
        return name;
    }

    public String getAuthor()
    {
        return author;
    }


}
