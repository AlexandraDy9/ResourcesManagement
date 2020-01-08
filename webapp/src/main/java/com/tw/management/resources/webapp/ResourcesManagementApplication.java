package com.tw.management.resources.webapp;

import com.tw.management.resources.service.ServiceModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(ServiceModule.class)
public class ResourcesManagementApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ResourcesManagementApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ResourcesManagementApplication.class, args);
    }
}