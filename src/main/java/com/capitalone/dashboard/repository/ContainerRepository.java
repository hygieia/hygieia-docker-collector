package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Container;
import com.capitalone.dashboard.model.Node;

public interface ContainerRepository<T extends Container> extends CrudRepository<T, ObjectId> {

	@Query(value = "{'containerId': ?0}")
	public Container findByContainerId(String containerId);

}
