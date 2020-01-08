package com.tw.management.resources.webapp.api;

import com.tw.management.resources.service.right.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/right")
public class RightController {

    private final RightService rightService;

    @Autowired
    public RightController(RightService rightService) {
        this.rightService = rightService;
    }
}
