package com.tw.management.resources.service.resource;


import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.model.TopicDao;
import com.tw.management.resources.persistence.resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceDao> getAll() {
        return ResourceConverter.convertToDaoList(resourceRepository.findAll());
    }

    public ResourceDao getById(long id) {
        return ResourceConverter.convertToDao(resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource with Id:" + id)));
    }
}
