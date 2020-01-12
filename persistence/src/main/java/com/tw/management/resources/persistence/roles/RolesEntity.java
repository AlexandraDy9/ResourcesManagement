package com.tw.management.resources.persistence.roles;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.right.RightsEntity;
import com.tw.management.resources.persistence.user.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "roles")
@Inheritance(strategy = InheritanceType.JOINED)
public class RolesEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private ResourceEntity resource;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Role_Right",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "right_id")})
    private List<RightsEntity> rights;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    public RolesEntity() {}

    public RolesEntity(ResourceEntity resource, List<RightsEntity> rights, List<UserEntity> users) {
        this.resource = resource;
        this.rights = rights;
        this.users = users;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public List<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(List<RightsEntity> rights) {
        this.rights = rights;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
