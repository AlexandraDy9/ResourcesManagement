package com.tw.management.resources.webapp.api;


import com.tw.management.resources.model.TopicDao;
import com.tw.management.resources.service.resource.ResourceService;
import com.tw.management.resources.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(value = "/resources")
public class ResourceController {

    private final ResourceService resourceService;
    private final TopicService topicService;

    @Autowired
    public ResourceController(ResourceService resourceService, TopicService topicService) {
        this.resourceService = resourceService;
        this.topicService = topicService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("resourcesList", resourceService.getAll());
        return "resources";
    }

    @GetMapping(value = "/{id}")
    public String openTopic(@PathVariable("id") long id, Model model) {
        List<TopicDao> topics = topicService.getTopicsForSpecificResource(id);
        model.addAttribute("topics", topics);
        model.addAttribute("resource", resourceService.getById(id));
        model.addAttribute("addTopic", new TopicDao());

        return "topic";
    }
}
