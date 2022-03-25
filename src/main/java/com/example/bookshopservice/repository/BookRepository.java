package com.example.bookshopservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository <Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.id= :id")
    Book findBookById(@Param("id") int id);

    @Query("SELECT b FROM Book b WHERE b.name= :name")
    Book findBookByName(@Param("name") String name);

    /*@Query("SELECT b FROM Book b WHERE b.author_id = :author_id")
    List<Book> findBookByAuthor(@Param("author_id") Integer author_id);

    @Query("SELECT b FROM Book b WHERE b.publisher_id = :publisher_id")
    List<Book> findBookByPublisher(@Param("publisher_id") Integer publisher_id);
    */
}
