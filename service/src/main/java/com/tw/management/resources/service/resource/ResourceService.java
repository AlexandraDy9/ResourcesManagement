package com.tw.management.resources.service.resource;


import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.resource.ResourceRepository;
import javassist.NotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
        if(!resourceRepository.existsByTitle(title)) {
            throw new NoSuchElementException("Resource with title" + title + " was not found");
        }
        else {
            return ResourceConverter.convertToDao(resourceRepository.findByTitle(title));
        }
    }

    @Transactional
    public void add(ResourceDao resourceDao) {
        if(resourceDao.getTitle().equals("")) {
            throw new NullPointerException("Title must not be null");
        }
        else {
            resourceRepository.save(ResourceConverter.convertFromDao(resourceDao));
        }
    }

    @Transactional
    public void delete(String title) {
        if(title.equals("")) {
            throw new NullPointerException("Resource is not available");
        }
        else {
            if(!resourceRepository.existsByTitle(title)) {
                throw new NoSuchElementException("Resource with title" + title + " was not found");
            }

            else {
                ResourceEntity resource = resourceRepository.findByTitle(title);
                resourceRepository.delete(resource);
            }
        }
    }

    @Transactional
    public void update(String title, ResourceDao newResource) {
        if(title.equals("")) {
            throw new NullPointerException("Resource is not available");
        }
        else {
            if(!resourceRepository.existsByTitle(title)) {
                throw new NoSuchElementException("Resource with title" + title + " was not found");
            }

            else {
                ResourceEntity currentResource = resourceRepository.findByTitle(title);
                currentResource.setTitle(newResource.getTitle());
                currentResource.setDescription(newResource.getDescription());
                currentResource.setPhoto(newResource.getPhoto());
                resourceRepository.save(currentResource);
            }
        }
    }
}
