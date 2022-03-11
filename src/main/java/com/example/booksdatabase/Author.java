package com.example.booksdatabase;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
