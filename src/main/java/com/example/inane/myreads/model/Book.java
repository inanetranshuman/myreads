package com.example.inane.myreads.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
    private String bookUID;
    private String title;
    private String subTitle;
    private String authorUID;
    private String publisherUID;
    private Date publishedDate;
    private String category;
    private String subCategory;
    private String coverImageUrl;
    private String language;
    private String tags;
    private String description;
    
    @Id
    public String getBookUID() {
        return bookUID;
    }
    public void setBookUID(String bookUID) {
        this.bookUID = bookUID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getAuthorUID() {
        return authorUID;
    }
    public void setAuthorUID(String authorUID) {
        this.authorUID = authorUID;
    }
    public String getPublisherUID() {
        return publisherUID;
    }
    public void setPublisherUID(String publisherUID) {
        this.publisherUID = publisherUID;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    

}
