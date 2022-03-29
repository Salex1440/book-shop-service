package com.example.bookshopservice.dto;

import com.example.bookshopservice.repository.Author;
import com.example.bookshopservice.repository.Book;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorDto {

    private int id;

    private String name;

    private List<String> books;

    public AuthorDto(Author author) {
        id = author.getId();
        name = author.getName();
        books = new ArrayList<>();
        author.getBooks().forEach(book -> books.add(book.getName()));
    }

}
