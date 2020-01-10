package com.tw.management.resources.service.user;

import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.persistence.user.Role;
import com.tw.management.resources.persistence.user.UserEntity;
import com.tw.management.resources.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(UserDao user) {
        String encodedPassword = passwordEncoder.encode(
                user.getPassword()
        );

        user.setPassword(encodedPassword);

        userRepository.save(UserConverter.convertFromDao(user));
    }

//    @PostConstruct
//    @Transactional
//    public void createAdmin() {
//        UserEntity user = new UserEntity(
//                "admin",
//                "admin",
//                new HashSet<>(Collections.singletonList(Role.ROLE_ADMIN)),
//                new ArrayList<>()
//        );
//    }
}
