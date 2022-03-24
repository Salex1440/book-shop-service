package com.example.bookshopservice.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Entity
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;
}
