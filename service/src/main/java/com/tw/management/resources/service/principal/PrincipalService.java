package com.tw.management.resources.service.principal;

import com.tw.management.resources.persistence.user.UserEntity;
import com.tw.management.resources.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
class PrincipalService {
    private final UserRepository userRepository;

    @Autowired
    public PrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getPrincipal() {
        String username = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof String) {
            username = (String) principal;
        }

        else if(principal instanceof UserEntity) {
            username = (String) ((UserEntity) principal).getUsername();
        }
//        else {
//            throw EntityNotFoundException("Can't retrieve username!");
//        }

        return userRepository.findByUsername(username);
    }
}

