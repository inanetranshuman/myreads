package com.example.inane.myreads.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class AuthorRepresentation extends RepresentationModel<AuthorRepresentation> {
    private String authorUID;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private Date deathDate;
    private String website;

    public String getAuthorUID() {
        return authorUID;
    }
    public void setAuthorUID(String authorUID) {
        this.authorUID = authorUID;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String first) {
        this.firstName = first;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date date) {
        this.birthDate = date;
    }
    public Date getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(Date date) {
        this.deathDate = date;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String url) {
        this.website = url;
    }
    

}
