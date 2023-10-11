package com.example.inane.myreads.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inane.myreads.dto.AuthorRepresentation;
import com.example.inane.myreads.model.Author;
import com.example.inane.myreads.service.AuthorService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value="/{id}", produces = { "application/hal+json" })
    public ResponseEntity<AuthorRepresentation> getAuthorById(@PathVariable String id) {
        Author authorEntity = authorService.findAuthorById(id);

        if (authorEntity == null) {
            return ResponseEntity.notFound().build();
        }

        AuthorRepresentation authorDto = modelMapper.map(authorEntity, AuthorRepresentation.class);

        // Add self-link to the authorDto
        Link selfLink = linkTo(methodOn(AuthorController.class).getAuthorById(id)).withSelfRel();
        authorDto.add(selfLink);

        return ResponseEntity.ok(authorDto);
    }
    

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<AuthorRepresentation> getAllBooks() {
        List<Author> authorEntities = authorService.findAllAuthors();

        List<AuthorRepresentation> authorDtos = authorEntities.stream()
                .map(entity -> modelMapper.map(entity, AuthorRepresentation.class))
                .collect(Collectors.toList());

        // Add HATEOAS links to each authorDto
        for (AuthorRepresentation authorDto : authorDtos) {
            String authorUID = authorDto.getAuthorUID(); // Assuming BookDto has a method to get ID
            Link selfLink = linkTo(methodOn(AuthorController.class).getAuthorById(authorUID)).withSelfRel();
            authorDto.add(selfLink);
        }

        Link link = linkTo(AuthorController.class).withSelfRel();
        CollectionModel<AuthorRepresentation> result = CollectionModel.of(authorDtos, link);
        return result;
    }
}
