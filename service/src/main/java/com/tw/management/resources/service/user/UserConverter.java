package com.tw.management.resources.service.user;

import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.persistence.user.UserEntity;

import java.util.ArrayList;

class UserConverter {

    static UserEntity convertFromDao(UserDao user) {
        return new UserEntity(user.getUsername(), user.getPassword(), true, new ArrayList<>());
    }
}
