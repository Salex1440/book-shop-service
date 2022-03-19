package com.example.bookshopservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    @Query("SELECT a FROM Author a WHERE a.name= :name")
    Author findAuthorByName(@Param("name") String name);
}
