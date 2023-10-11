package com.example.inane.myreads.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inane.myreads.model.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    
}
