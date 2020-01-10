package com.tw.management.resources.service.resource;


import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ResourceDao getByTitle(String title) {
        return ResourceConverter.convertToDao(resourceRepository.findByTitle(title));
    }

    @Transactional
    public void add(ResourceDao resourceDao) {
        resourceRepository.save(ResourceConverter.convertFromDao(resourceDao));
    }

    @Transactional
    public void delete(String title) {
        ResourceEntity resource = resourceRepository.findByTitle(title);
        resourceRepository.delete(resource);
    }

    @Transactional
    public void update(String title, ResourceDao newResource) {
        ResourceEntity currentResource = resourceRepository.findByTitle(title);
        currentResource.setTitle(newResource.getTitle());
        currentResource.setDescription(newResource.getDescription());
        currentResource.setPhoto(newResource.getPhoto());
        resourceRepository.save(currentResource);
    }
}
