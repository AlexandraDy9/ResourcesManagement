package com.tw.management.resources.persistence.resource;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.right.RightsEntity;
import com.tw.management.resources.persistence.topic.TopicEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class ResourceEntity extends BaseEntity {

    @Column(unique = true)
    @NotEmpty
    private String title;

    @NotEmpty
    private String photo;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<TopicEntity> topics;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<RightsEntity> rights;

    public ResourceEntity() { }

    public ResourceEntity(String title,String photo, List<TopicEntity> topics) {
        this.title = title;
        this.photo = photo;
        this.topics = topics;
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

    public List<TopicEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicEntity> topics) {
        this.topics = topics;
    }

    public List<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(List<RightsEntity> rights) {
        this.rights = rights;
    }
}
