package com.tw.management.resources.persistence.resource;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.roles.RolesEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class ResourceEntity extends BaseEntity {

    @Column(unique = true)
    @NotEmpty
    private String title;

    private String photo;

    @Size(max=1000)
    private String description;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<RolesEntity> roles;

    public ResourceEntity() { }

    public ResourceEntity(String title, String photo, String description, List<RolesEntity> roles) {
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.roles = roles;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> rights) {
        this.roles = roles;
    }
}
