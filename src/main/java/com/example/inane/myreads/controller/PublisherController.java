package com.example.inane.myreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inane.myreads.dto.PublisherRepresentation;
import com.example.inane.myreads.dto.assembler.PublisherModelAssembler;
import com.example.inane.myreads.exception.EntityNotFoundException;
import com.example.inane.myreads.model.Publisher;
import com.example.inane.myreads.service.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherModelAssembler assembler;

    @GetMapping(value="/{id}", produces = { "application/hal+json" })
    public EntityModel<PublisherRepresentation> getPublisherById(@PathVariable String id) {
        Publisher publisherEntity = publisherService.findPublisherById(id);

        if (publisherEntity == null) {
            throw new EntityNotFoundException(id);
        }

        return assembler.toModel(publisherEntity);       
    }

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<EntityModel<PublisherRepresentation>> getAllPublishers() {
        List<Publisher> publisherEntities = publisherService.findAllPublishers();

        return assembler.toCollectionModel(publisherEntities);
    }

}
