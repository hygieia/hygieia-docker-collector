package com.capitalone.dashboard.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Image;
import com.capitalone.dashboard.model.Node;

public interface ImageRepository<T extends Image> extends CrudRepository<T, ObjectId> {

	@Query(value = "{'imageId': ?0}")
	public Image findByImageId(String imageId);

}
