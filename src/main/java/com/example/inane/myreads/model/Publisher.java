package com.example.inane.myreads.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="publishers")
public class Publisher {
    private String publisherUID;
    private String name;
    private String description;
    private Date foundedDate;
    private String contactNumber;
    private String country;

   @Id
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
    public String getDescription() {
        return description;
    }
    public Date getFoundedDate() {
        return foundedDate;
    }
    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String number) {
        this.contactNumber = number;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    


}
