package com.example.inane.myreads.dto.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.inane.myreads.controller.AuthorController;
import com.example.inane.myreads.dto.AuthorRepresentation;
import com.example.inane.myreads.model.Author;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;

@Component
public class AuthorModelAssembler implements RepresentationModelAssembler<Author, EntityModel<AuthorRepresentation>> {
    
    @Autowired
    ModelMapper modelMapper;

     @Override
    public EntityModel<AuthorRepresentation> toModel(Author author) {
        AuthorRepresentation authorDto = modelMapper.map(author, AuthorRepresentation.class);

        EntityModel<AuthorRepresentation> model = EntityModel.of(authorDto,
                linkTo(methodOn(AuthorController.class).getAuthorById(author.getAuthorUID())).withSelfRel(),
                linkTo(methodOn(AuthorController.class).getAllAuthors()).withRel("authors"));

       
        return model;
    }

   @Override
    public CollectionModel<EntityModel<AuthorRepresentation>> toCollectionModel(Iterable<? extends Author> authorEntities) {
        List<EntityModel<AuthorRepresentation>> entityModels = StreamSupport.stream(authorEntities.spliterator(), false)
        .map(entity -> {
            AuthorRepresentation authorDto = modelMapper.map(entity, AuthorRepresentation.class);
            String authorUID = authorDto.getAuthorUID();
            Link selfLink = linkTo(methodOn(AuthorController.class).getAuthorById(authorUID)).withSelfRel();
            return EntityModel.of(authorDto, selfLink);
        })
        .collect(Collectors.toList());

        Link link = linkTo(AuthorController.class).withSelfRel();
        return CollectionModel.of(entityModels, link);
    }
}
