package com.tw.management.resources.service.user;

import com.tw.management.resources.model.UserDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.resource.ResourceRepository;
import com.tw.management.resources.persistence.right.RightRepository;
import com.tw.management.resources.persistence.right.Rights;
import com.tw.management.resources.persistence.right.RightsEntity;
import com.tw.management.resources.persistence.roles.RolesEntity;
import com.tw.management.resources.persistence.roles.RolesRepository;
import com.tw.management.resources.persistence.user.UserEntity;
import com.tw.management.resources.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;
    private final RolesRepository rolesRepository;
    private final RightRepository rightRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       ResourceRepository resourceRepository,
                       RolesRepository rolesRepository,
                       RightRepository rightRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
        this.rolesRepository = rolesRepository;
        this.rightRepository = rightRepository;
    }

    public List<UserDao> getAllUsers() {
        List<UserEntity> usersList = userRepository
                .findAll()
                .stream()
                .filter(UserEntity::getAdmin)
                .collect(Collectors.toList());

        return UserConverter.convertToDaoList(usersList);
    }

    public Map<String, List<Rights>> getRights(UserEntity userEntity) {
        Map<String, List<Rights>> rightsMap = new HashMap<>();

        userEntity.getRoles().forEach((role) -> role.getRights().forEach((right) -> {
            if (rightsMap.containsKey(role.getResource().getTitle())) {
                rightsMap.get(role.getResource().getTitle()).add(right.getRightType());
            }
            else {
                rightsMap.put(role.getResource().getTitle(), new ArrayList<Rights>() {{
                    add(right.getRightType());
                }});
            }
        }));

        return rightsMap;
    }

    public void assignRights(String resourceTitle, String username, String right){
        ResourceEntity resource;

        if(!resourceRepository.existsByTitle(resourceTitle)) {
            throw new NoSuchElementException("Resource with title" + resourceTitle + " was not found");
        }

        else {
            resource = resourceRepository.findByTitle(resourceTitle);
        }

        if(!rolesRepository.existsByResource(resource)) {
            rolesRepository.save(new RolesEntity(resource));
        }

        RolesEntity role = rolesRepository.findByResourceTitle(resourceTitle);
        RightsEntity rightsEntity = rightRepository.findByRightType(Rights.valueOf(right));

        role.getRights().add(rightsEntity);
        rolesRepository.save(role);

        UserEntity user = userRepository.findByUsername(username);
        user.getRoles().add(role);

        userRepository.save(user);
    }
}
