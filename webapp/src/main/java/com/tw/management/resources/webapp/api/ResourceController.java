package com.tw.management.resources.webapp.api;


import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());

        return "resources";
    }


    @GetMapping("edit/{title}")
    public String showUpdateForm(@PathVariable("title") String title, Model model) {
        model.addAttribute("resource", resourceService.getByTitle(title));
        model.addAttribute("updateResource", new ResourceDao());

        return "update-resource";
    }

    @PostMapping("update/{title}")
    public String update(@PathVariable("title") String title, @Valid ResourceDao resourceDao, BindingResult result, Model model) {
        if (result.hasErrors()) {
            resourceDao.setTitle(title);
            return "update-resource";
        }

        resourceService.update(title, resourceDao);
        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());

        return "resources";
    }

    @PostMapping("add")
    public String add(@Valid ResourceDao resourceDao, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "resources";
        }

        resourceService.add(resourceDao);
        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());

        return "resources";
    }

    @GetMapping("delete/{title}")
    public String delete(@PathVariable("title") String title, Model model) {
        resourceService.delete(title);
        model.addAttribute("resourcesList", resourceService.getAll());
        model.addAttribute("addResource", new ResourceDao());

        return "resources";

    }
}
