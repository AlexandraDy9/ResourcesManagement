package com.tw.management.resources.webapp.api;

import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.persistence.user.UserEntity;
import com.tw.management.resources.service.principal.PrincipalService;
import com.tw.management.resources.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(value = "/admin")
public class UserController {

    private final UserService userService;
    private final PrincipalService principalService;

    @Autowired
    public UserController(UserService userService, PrincipalService principalService) {
        this.userService = userService;
        this.principalService = principalService;
    }


    @GetMapping
    public String get(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());

        return "admin";
    }

    @PostMapping(value = "/register")
    public UserDao register(@Valid @RequestBody UserDao user) {
        userService.register(user);
        return user;
    }

    @GetMapping(value = "/principal")
    public UserEntity getPrincipal() {
        return principalService.getPrincipal();
    }


    @GetMapping(value = "/{title}")
    public String getAdminProprieties(@PathVariable("title") String title, Model model) {
//        ResourceDao resourceDao = new ResourceDao();
//
//        try {
//            resourceDao = resourceService.getByTitle(title);
//        } catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//        }
//
        model.addAttribute("usersList", userService.getAllUsers());

        return "admin";
    }
}
