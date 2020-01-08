package com.tw.management.resources.persistence.topic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    TopicEntity findAllByTitle(String title);
}
