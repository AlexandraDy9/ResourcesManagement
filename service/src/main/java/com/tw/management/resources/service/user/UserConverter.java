package com.tw.management.resources.service.user;

import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.persistence.user.UserEntity;

import java.util.*;

class UserConverter {

    static UserDao convertToDao(UserEntity user) {
        return new UserDao(
                user.getUsername(),
                user.getPassword()
        );
    }

    static UserEntity convertFromDao(UserDao user) {
        return new UserEntity(
                user.getUsername(),
                user.getPassword(),
                true,
                new HashSet<>()
        );
    }

    static List<UserDao> convertToDaoList(List<UserEntity> categoryList) {
        List<UserDao> resourceDaoList = new ArrayList<>();
        categoryList.forEach(category -> resourceDaoList.add(convertToDao(category)));

        return resourceDaoList;
    }
}
