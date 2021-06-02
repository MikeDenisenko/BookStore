package com.mike.service;

import com.mike.model.Book;
import com.mike.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book find(int id) {
        return bookRepository.find(id);
    }

    public Book save(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public void update(Book book) {
        bookRepository.saveAndFlush(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> getVisibleBooks() {
        return bookRepository.getVisibleBooks();
    }
}



