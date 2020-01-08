package com.tw.management.resources.webapp.api;

import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userRepository) {
        this.userService = userRepository;
    }


    @PostMapping(value = "/register")
    public UserDao register(@Valid @RequestBody UserDao user) {
        userService.register(user);
        return user;
    }
}
