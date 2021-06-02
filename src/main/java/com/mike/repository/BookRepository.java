package com.mike.repository;

import com.mike.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.name = :name")
    Book find(@Param("name") String name);

    @Query("select b from Book b where b.id = :id")
    Book find(@Param("id") int id);

    @Query("select b from Book b where b.visibility = true")
    List<Book> getVisibleBooks();
}




