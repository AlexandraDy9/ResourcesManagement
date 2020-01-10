package com.tw.management.resources.model;

public class ResourceDao {

    private String title;

    private String description;

    private String photo;

    public ResourceDao() {
    }

    public ResourceDao(String title, String description, String photo) {
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
