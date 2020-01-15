package com.tw.management.resources.persistence.roles;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.right.RightsEntity;
import com.tw.management.resources.persistence.user.UserEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "roles")
@Inheritance(strategy = InheritanceType.JOINED)
public class RolesEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private ResourceEntity resource;

    @ManyToMany
    @JoinTable(name = "Role_Right",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "right_id")})
    private Set<RightsEntity> rights;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public RolesEntity() {}

    public RolesEntity(ResourceEntity resource) {
        this.resource = resource;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public Set<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(Set<RightsEntity> rights) {
        this.rights = rights;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }
}
