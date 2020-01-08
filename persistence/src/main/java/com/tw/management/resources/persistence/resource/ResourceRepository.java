package com.tw.management.resources.persistence.resource;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    ResourceEntity findByTitle(String title);
}
