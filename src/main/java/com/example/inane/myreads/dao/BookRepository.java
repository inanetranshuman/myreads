package com.example.inane.myreads.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inane.myreads.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    
}
