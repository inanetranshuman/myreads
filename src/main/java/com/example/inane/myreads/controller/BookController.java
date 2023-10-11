package com.example.inane.myreads.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inane.myreads.dto.BookRepresentation;
import com.example.inane.myreads.dto.assembler.BookModelAssembler;
import com.example.inane.myreads.exception.BookNotFoundException;
import com.example.inane.myreads.model.Book;
import com.example.inane.myreads.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    BookService bookService;

    @Autowired
    private BookModelAssembler assembler;

    @GetMapping(value="/{id}", produces = { "application/hal+json" })
    public EntityModel<BookRepresentation> getBookById(@PathVariable String id) {
        Book bookEntity = bookService.findBookById(id);

        if (bookEntity == null) {
            throw new BookNotFoundException(id);
        }

        return assembler.toModel(bookEntity);
    }
    

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<EntityModel<BookRepresentation>> getAllBooks() {
        List<Book> bookEntities = bookService.findAllBooks();

        return assembler.toCollectionModel(bookEntities);
    }

}
