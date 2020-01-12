package com.tw.management.resources.persistence.right;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.roles.RolesEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity(name = "rights")
@Inheritance(strategy = InheritanceType.JOINED)
public class RightsEntity extends BaseEntity {
    @NotEmpty
    private Rights rightType;

    @ManyToMany(mappedBy = "rights")
    private List<RolesEntity> roles;

    public RightsEntity() { }

    public Rights getRightType() {
        return rightType;
    }

    public void setRightType(Rights rightType) {
        this.rightType = rightType;
    }

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }
}
