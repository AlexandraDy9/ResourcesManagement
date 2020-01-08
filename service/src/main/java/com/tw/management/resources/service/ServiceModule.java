package com.tw.management.resources.service;

import com.tw.management.resources.persistence.PersistenceModule;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(PersistenceModule.class)
public class ServiceModule {
}
