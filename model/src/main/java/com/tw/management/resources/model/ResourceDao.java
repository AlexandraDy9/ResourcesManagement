package com.tw.management.resources.model;

public class ResourceDao {

    private long id;

    private String title;

    private String photo;

    public ResourceDao() {
    }

    public ResourceDao(long id, String title, String photo) {
        this.id = id;
        this.title = title;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
