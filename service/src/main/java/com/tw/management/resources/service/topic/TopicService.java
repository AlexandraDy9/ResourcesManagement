package com.tw.management.resources.service.topic;

import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.model.TopicDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.resource.ResourceRepository;
import com.tw.management.resources.persistence.topic.TopicEntity;
import com.tw.management.resources.persistence.topic.TopicRepository;
import com.tw.management.resources.service.resource.ResourceConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final ResourceRepository resourceRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository, ResourceRepository resourceRepository) {
        this.topicRepository = topicRepository;
        this.resourceRepository = resourceRepository;
    }


    public List<TopicDao> getTopicsForSpecificResource(long id) {
        return TopicConverter.convertToDaoList (
                topicRepository.findAll()
                        .stream().filter(topic-> topic.getResource().getId().equals(id))
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public void add(TopicDao topic, String resourceTitle) {
        ResourceEntity resourceEntity = resourceRepository.findByTitle(resourceTitle);
        topicRepository.save(TopicConverter.convertFromDao(topic, resourceEntity));
    }

    @Transactional
    public void delete(String title) {
        TopicEntity resource = topicRepository.findAllByTitle(title);
        topicRepository.delete(resource);
    }

    @Transactional
    public void update(String title, TopicDao newResource) {
        TopicEntity currentResource = topicRepository.findAllByTitle(title);
        currentResource.setTitle(newResource.getTitle());
        currentResource.setDescription(newResource.getDescription());
        topicRepository.save(currentResource);
    }

    public List<TopicDao> getAll() {
        return  TopicConverter.convertToDaoList(topicRepository.findAll());
    }
}
