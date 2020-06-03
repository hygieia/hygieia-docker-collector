
package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Node;

public interface NodeRepository<T extends Node> extends CrudRepository<T, ObjectId> {
	@Query(value = "{'nodeId': ?0}")
	public Node findByNodeId(String nodeId);
}
