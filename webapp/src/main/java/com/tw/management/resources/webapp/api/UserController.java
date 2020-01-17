package com.tw.management.resources.webapp.api;

import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.model.RightDto;
import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.persistence.right.Rights;
import com.tw.management.resources.persistence.user.UserEntity;
import com.tw.management.resources.service.principal.PrincipalService;
import com.tw.management.resources.service.resource.ResourceService;
import com.tw.management.resources.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;


@Controller
public class UserController {

    private final ResourceService resourceService;
    private final UserService userService;
    private final PrincipalService principalService;

    @Autowired
    public UserController(ResourceService resourceService, UserService userService, PrincipalService principalService) {
        this.resourceService = resourceService;
        this.userService = userService;
        this.principalService = principalService;
    }

    @RequestMapping(value="/login")
    public String homePage() {
        return "login";
    }

    @GetMapping(value = "/principal")
    public UserEntity getPrincipal() {
        return principalService.getPrincipal();
    }

    @GetMapping(value = "/admin/{title}")
    public String ListsForAdmin(@PathVariable("title") String title, Model model) {
        model.addAttribute("resourceTitle", title);
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("rightsList", new ArrayList<>(Arrays.asList(Rights.values())));
        model.addAttribute("addRight", new RightDto());

        return "admin";
    }

    @PostMapping(value = "/admin/assignment/{title}")
    public String AssignmentAdmin(@PathVariable("title") String title, @Valid RightDto rightDto, Model model) {

        try {
            userService.assignRights(title, rightDto.getUser(), rightDto.getRight());
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());
        model.addAttribute("currentUser", principalService.getPrincipal());
        model.addAttribute("userRights", userService.getRights(principalService.getPrincipal()));

        return "resources";
    }
}
