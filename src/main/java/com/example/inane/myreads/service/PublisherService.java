package com.example.inane.myreads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inane.myreads.dao.PublisherRepository;
import com.example.inane.myreads.model.Publisher;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    public Publisher findPublisherById(String publisherUID) {
        return publisherRepository.findById(publisherUID).get();
    }

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }
}
