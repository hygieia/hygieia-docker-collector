package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Node;
import com.capitalone.dashboard.model.Task;

public interface TaskRepository<T extends Task> extends CrudRepository<T, ObjectId> {

	@Query(value = "{'taskId': ?0}")
	public Task findByTaskId(String taskId);

}
