package com.tw.management.resources.webapp.api;


import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.model.RightDto;
import com.tw.management.resources.persistence.right.Rights;
import com.tw.management.resources.service.principal.PrincipalService;
import com.tw.management.resources.service.resource.ResourceService;
import com.tw.management.resources.service.user.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;


@Controller
@RequestMapping(value = "/resources")
public class ResourceController {

    private final ResourceService resourceService;
    private final PrincipalService principalService;
    private final UserService userService;


    @Autowired
    public ResourceController(ResourceService resourceService, PrincipalService principalService, UserService userService) {
        this.resourceService = resourceService;
        this.principalService = principalService;
        this.userService = userService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());
        model.addAttribute("currentUser", principalService.getPrincipal());
        model.addAttribute("userRights", userService.getRights(principalService.getPrincipal()));

        return "resources";
    }

    @GetMapping("edit/{title}")
    public String showUpdateForm(@PathVariable("title") String title, Model model) {
        ResourceDao resourceDao = new ResourceDao();

        try {
            resourceDao = resourceService.getByTitle(title);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        model.addAttribute("resource", resourceDao);
        model.addAttribute("updateResource", new ResourceDao());
        model.addAttribute("userRights", userService.getRights(principalService.getPrincipal()));

        return "update-resource";
    }

    @PostMapping("update/{title}")
    public String update(@PathVariable("title") String title, @Valid ResourceDao resourceDao, BindingResult result, Model model) {
        if (result.hasErrors()) {
            resourceDao.setTitle(title);
            return "update-resource";
        }

        try {
            resourceService.update(title, resourceDao);
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());
        model.addAttribute("currentUser", principalService.getPrincipal());
        model.addAttribute("userRights", userService.getRights(principalService.getPrincipal()));

        return "resources";
    }

    @PostMapping("add")
    public String add(@Valid ResourceDao resourceDao, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "resources";
        }

        try {
            resourceService.add(resourceDao);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());
        model.addAttribute("currentUser", principalService.getPrincipal());
        model.addAttribute("userRights", userService.getRights(principalService.getPrincipal()));

        return "resources";
    }

    @GetMapping("delete/{title}")
    public String delete(@PathVariable("title") String title, Model model) {
        try {
            resourceService.delete(title);
        } catch (NullPointerException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());
        model.addAttribute("currentUser", principalService.getPrincipal());
        model.addAttribute("userRights", userService.getRights(principalService.getPrincipal()));

        return "resources";
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
