package com.example.inane.myreads.dto.assembler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.inane.myreads.controller.PublisherController;
import com.example.inane.myreads.dto.PublisherRepresentation;
import com.example.inane.myreads.model.Publisher;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PublisherModelAssembler implements RepresentationModelAssembler<Publisher, EntityModel<PublisherRepresentation>> {
    
    @Autowired
    ModelMapper modelMapper;

     @Override
    public EntityModel<PublisherRepresentation> toModel(Publisher publisher) {
        PublisherRepresentation publisherDto = modelMapper.map(publisher, PublisherRepresentation.class);

        EntityModel<PublisherRepresentation> model = EntityModel.of(publisherDto,
                linkTo(methodOn(PublisherController.class).getPublisherById(publisher.getPublisherUID())).withSelfRel(),
                linkTo(methodOn(PublisherController.class).getAllPublishers()).withRel("publishers"));

       
        return model;
    }

   @Override
    public CollectionModel<EntityModel<PublisherRepresentation>> toCollectionModel(Iterable<? extends Publisher> publisherEntities) {
        List<EntityModel<PublisherRepresentation>> entityModels = StreamSupport.stream(publisherEntities.spliterator(), false)
        .map(entity -> {
            PublisherRepresentation publisherDto = modelMapper.map(entity, PublisherRepresentation.class);
            String publisherUID = publisherDto.getPublisherUID();
            Link selfLink = linkTo(methodOn(PublisherController.class).getPublisherById(publisherUID)).withSelfRel();
            return EntityModel.of(publisherDto, selfLink);
        })
        .collect(Collectors.toList());

        Link link = linkTo(PublisherController.class).withSelfRel();
        return CollectionModel.of(entityModels, link);
    }
}
