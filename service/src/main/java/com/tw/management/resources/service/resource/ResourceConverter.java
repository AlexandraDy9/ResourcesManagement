package com.tw.management.resources.service.resource;

import com.tw.management.resources.model.ResourceDao;
import com.tw.management.resources.persistence.resource.ResourceEntity;
import java.util.ArrayList;
import java.util.List;

public class ResourceConverter {

    public static ResourceDao convertToDao(ResourceEntity resource) {
        return new ResourceDao(
                resource.getId(),
                resource.getTitle(),
                resource.getPhoto()
        );
    }

    public static ResourceEntity convertFromDao(ResourceDao resource) {
        return new ResourceEntity(
                resource.getTitle(),
                resource.getPhoto(),
                new ArrayList<>()
        );
    }

    public static List<ResourceDao> convertToDaoList(List<ResourceEntity> categoryList) {
        List<ResourceDao> resourceDaoList = new ArrayList<>();
        categoryList.forEach(category -> resourceDaoList.add(convertToDao(category)));

        return resourceDaoList;
    }
}
