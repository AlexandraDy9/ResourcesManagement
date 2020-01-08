package com.tw.management.resources.service.topic;

import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.model.TopicDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import com.tw.management.resources.persistence.topic.TopicEntity;
import com.tw.management.resources.service.resource.ResourceConverter;
import java.util.ArrayList;
import java.util.List;


public class TopicConverter {

    static TopicDao convertToDao(TopicEntity resource) {
        return new TopicDao(
                resource.getTitle(),
                resource.getDescription()
        );
    }

    static TopicEntity convertFromDao(TopicDao topicDao, ResourceEntity resourceEntity) {
        return new TopicEntity(
                topicDao.getTitle(),
                topicDao.getDescription(),
                resourceEntity);
    }

    static List<TopicDao> convertToDaoList(List<TopicEntity> topicList) {
        List<TopicDao> topicDaoList = new ArrayList<>();
        topicList.forEach(resource -> topicDaoList.add(convertToDao(resource)));

        return topicDaoList;
    }
}
