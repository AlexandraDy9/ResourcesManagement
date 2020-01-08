package com.tw.management.resources.persistence.topic;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.right.RightsEntity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity(name = "topic")
@Inheritance(strategy = InheritanceType.JOINED)
public class TopicEntity extends BaseEntity {
    @Column(unique = true)
    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_resource")
    private ResourceEntity resource;

    public TopicEntity() { }

    public TopicEntity(String title, String description, ResourceEntity resource) {
        this.title = title;
        this.description = description;
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }
}
