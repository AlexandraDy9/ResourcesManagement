package com.tw.management.resources.webapp.api;

import com.tw.management.resources.model.TopicDao;
import com.tw.management.resources.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("topics", topicService.getAll());

        return "topic";
    }

    @PostMapping("add/{resource}")
    public String add(@PathVariable("resource") String resource, @Valid TopicDao topicDao, BindingResult result, Model model) {
        topicService.add(topicDao, resource);

        return "redirect:resources";
    }

    @GetMapping("delete/{title}")
    public String delete(@PathVariable("title") String title, Model model) {
        topicService.delete(title);
        model.addAttribute("topics", topicService.getAll());

        return "topic";
    }

}
