package com.tw.management.resources.persistence.resource;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.right.RightsEntity;

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

    @NotEmpty
    private String photo;

    @NotEmpty
    @Size(max=600)
    private String description;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<RightsEntity> rights;

    public ResourceEntity() { }

    public ResourceEntity(@NotEmpty String title, @NotEmpty String photo, @NotEmpty String description, List<RightsEntity> rights) {
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.rights = rights;
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

    public List<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(List<RightsEntity> rights) {
        this.rights = rights;
    }
}
