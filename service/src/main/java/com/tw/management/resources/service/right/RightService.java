package com.tw.management.resources.service.right;

import com.tw.management.resources.persistence.right.RightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightService {

    private final RightRepository rightRepository;

    @Autowired
    public RightService(RightRepository rightRepository) {
        this.rightRepository = rightRepository;
    }
}
