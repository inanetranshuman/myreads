package com.example.inane.myreads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inane.myreads.dao.BookRepository;
import com.example.inane.myreads.model.Book;

@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(String bookUID) {
        return bookRepository.findById(bookUID).get();
    }
}
