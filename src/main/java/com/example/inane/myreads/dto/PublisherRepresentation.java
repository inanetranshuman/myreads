package com.example.inane.myreads.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class PublisherRepresentation extends RepresentationModel<PublisherRepresentation> {
    private String publisherUID;
    private String name;
    private String description;
    private Date foundedDate;
    private String contactNumber;
    private String country;

    public String getPublisherUID() {
        return publisherUID;
    }
    public void setPublisherUID(String publisherUID) {
        this.publisherUID = publisherUID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getFoundedDate() {
        return foundedDate;
    }
    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setContactNumber(String number) {
        this.contactNumber = number;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    
}
