package com.tw.management.resources.service.resource;


import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.resource.ResourceRepository;
import com.tw.management.resources.persistence.right.RightRepository;
import com.tw.management.resources.persistence.right.RightsEntity;
import com.tw.management.resources.persistence.roles.RolesEntity;
import com.tw.management.resources.persistence.roles.RolesRepository;
import com.tw.management.resources.persistence.user.UserEntity;
import com.tw.management.resources.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    private final RightRepository rightRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository,
                           RolesRepository rolesRepository,
                           RightRepository rightRepository,
                           UserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.rolesRepository = rolesRepository;
        this.rightRepository = rightRepository;
        this.userRepository = userRepository;
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
                List<RolesEntity> roles = resource.getRoles();

                List<RightsEntity> rights = rightRepository.findAll();
                for (RolesEntity role: roles) {
                    rights.forEach(right -> right.removeRole(role));
                }

                List<UserEntity> users = userRepository.findAll();
                for (UserEntity user: users) {
                    roles.forEach(role -> role.removeUser(user));
                }

                roles.forEach(rolesRepository::delete);
                resource.getRoles().removeAll(resource.getRoles());
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
