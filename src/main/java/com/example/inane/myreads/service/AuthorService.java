package com.example.inane.myreads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inane.myreads.dao.AuthorRepository;
import com.example.inane.myreads.model.Author;

@Service
public class AuthorService {
    
    @Autowired
    AuthorRepository authorRepository;

    public Author findAuthorById(String authorUID) {
        return authorRepository.findById(authorUID).get();
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

}
