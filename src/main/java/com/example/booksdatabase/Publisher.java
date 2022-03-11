package com.example.booksdatabase;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


public class Publisher {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private List<Book> books;

}
