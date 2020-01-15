package com.tw.management.resources.persistence.right;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.roles.RolesEntity;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity(name = "rights")
@Inheritance(strategy = InheritanceType.JOINED)
public class RightsEntity extends BaseEntity {
    @NotNull
    private Rights rightType;

    @ManyToMany(mappedBy = "rights")
    private Set<RolesEntity> roles;

    public RightsEntity() { }

    public RightsEntity(Rights rightType) {
        this.rightType = rightType;
    }

    public Rights getRightType() {
        return rightType;
    }

    public void setRightType(Rights rightType) {
        this.rightType = rightType;
    }

    public Set<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesEntity> roles) {
        this.roles = roles;
    }
}
