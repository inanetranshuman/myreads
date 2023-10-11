package com.example.inane.myreads.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super("Could not find book with id: " + id);
    }    
}
