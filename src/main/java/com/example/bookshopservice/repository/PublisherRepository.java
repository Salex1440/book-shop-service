package com.example.bookshopservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PublisherRepository extends CrudRepository<Publisher, Integer> {

    @Query("SELECT p FROM Publisher p WHERE p.name= :name")
    Publisher findPublisherByName(@Param("name") String name);
}
