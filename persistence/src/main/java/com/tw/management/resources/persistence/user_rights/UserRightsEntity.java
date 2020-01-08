package com.tw.management.resources.persistence.user_rights;

import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.right.RightsEntity;
import com.tw.management.resources.persistence.user.UserEntity;

import javax.persistence.*;

@Entity(name = "user_rights")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserRightsEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "fk_right")
    private RightsEntity right;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private UserEntity user;

    public UserRightsEntity() { }

    public RightsEntity getRight() {
        return right;
    }

    public void setRight(RightsEntity right) {
        this.right = right;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
