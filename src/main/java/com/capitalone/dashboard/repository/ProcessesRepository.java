package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Node;
import com.capitalone.dashboard.model.Processes;

public interface ProcessesRepository<T extends Processes> extends CrudRepository<T, ObjectId> {

	@Query(value = "{'containerId': ?0}")
	public Processes findByContainerId(String containerId);

}
