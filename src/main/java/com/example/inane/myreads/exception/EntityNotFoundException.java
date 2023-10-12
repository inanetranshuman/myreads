package com.example.inane.myreads.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id) {
        super("Could not find entity with id: " + id);
    }    
}
