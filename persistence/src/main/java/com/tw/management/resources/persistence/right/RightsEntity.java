package com.tw.management.resources.persistence.right;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.topic.TopicEntity;
import com.tw.management.resources.persistence.user_rights.UserRightsEntity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity(name = "rights")
@Inheritance(strategy = InheritanceType.JOINED)
public class RightsEntity extends BaseEntity {
    @NotEmpty
    private Rights rightType;

    @OneToMany(mappedBy = "right", cascade = CascadeType.ALL)
    private List<UserRightsEntity> users;

    @ManyToOne
    @JoinColumn(name = "fk_resource")
    private ResourceEntity resource;

    public RightsEntity() { }

    public Rights getRightType() {
        return rightType;
    }

    public void setRightType(Rights rightType) {
        this.rightType = rightType;
    }

    public List<UserRightsEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserRightsEntity> users) {
        this.users = users;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }
}
