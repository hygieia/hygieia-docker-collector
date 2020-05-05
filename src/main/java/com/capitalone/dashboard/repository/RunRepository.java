package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Run;

public interface RunRepository<T extends Run> extends CrudRepository<T, ObjectId> {

	T findByRunId(String runId);

	List<T> findByWorkspaceId(String workspaceId/* , String millis */);
}
