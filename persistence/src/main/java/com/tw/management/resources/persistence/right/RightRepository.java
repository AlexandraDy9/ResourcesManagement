package com.tw.management.resources.persistence.right;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RightRepository extends JpaRepository<RightsEntity, Long> {

    RightsEntity findByRightType(Rights rights);
}