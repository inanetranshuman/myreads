package com.example.inane.myreads.dto.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.inane.myreads.controller.AuthorController;
import com.example.inane.myreads.controller.BookController;
import com.example.inane.myreads.controller.PublisherController;
import com.example.inane.myreads.dto.BookRepresentation;
import com.example.inane.myreads.model.Book;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<BookRepresentation>> {
 
    @Autowired
    ModelMapper modelMapper;

    @Override
    public EntityModel<BookRepresentation> toModel(Book book) {
        BookRepresentation bookDto = modelMapper.map(book, BookRepresentation.class);


        EntityModel<BookRepresentation> model = EntityModel.of(bookDto,
                linkTo(methodOn(BookController.class).getBookById(book.getBookUID())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));

        // Add related resources
        model.add(linkTo(methodOn(AuthorController.class).getAuthorById(book.getAuthorUID())).withRel("author"));
        model.add(linkTo(methodOn(PublisherController.class).getPublisherById(book.getPublisherUID())).withRel("publisher"));

        return model;
    }

    @Override
    public CollectionModel<EntityModel<BookRepresentation>> toCollectionModel(Iterable<? extends Book> bookEntities) {
        List<EntityModel<BookRepresentation>> entityModels = StreamSupport.stream(bookEntities.spliterator(), false)
        .map(entity -> {
            BookRepresentation bookDto = modelMapper.map(entity, BookRepresentation.class);
            String bookUID = bookDto.getBookUID();
            Link selfLink = linkTo(methodOn(BookController.class).getBookById(bookUID)).withSelfRel();
            return EntityModel.of(bookDto, selfLink);
        })
        .collect(Collectors.toList());

        Link link = linkTo(BookController.class).withSelfRel();
        return CollectionModel.of(entityModels, link);
    }
}
