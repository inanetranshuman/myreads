package com.example.inane.myreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inane.myreads.dto.AuthorRepresentation;
import com.example.inane.myreads.dto.assembler.AuthorModelAssembler;
import com.example.inane.myreads.exception.EntityNotFoundException;
import com.example.inane.myreads.model.Author;
import com.example.inane.myreads.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorModelAssembler assembler;

    @GetMapping(value="/{id}", produces = { "application/hal+json" })
    public EntityModel<AuthorRepresentation> getAuthorById(@PathVariable String id) {
        Author authorEntity = authorService.findAuthorById(id);

        if (authorEntity == null) {
            throw new EntityNotFoundException(id);
        }

        return assembler.toModel(authorEntity);
    }
    

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<EntityModel<AuthorRepresentation>> getAllAuthors() {
        List<Author> authorEntities = authorService.findAllAuthors();

        return assembler.toCollectionModel(authorEntities);
    }
}
