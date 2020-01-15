package com.tw.management.resources.persistence.roles;

import com.tw.management.resources.persistence.resource.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    RolesEntity findByResourceTitle(String title);

    void deleteByResource(ResourceEntity resourceEntity);

    boolean existsByResource(ResourceEntity resourceEntity);
}
