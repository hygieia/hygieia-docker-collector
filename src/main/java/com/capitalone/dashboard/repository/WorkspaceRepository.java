package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Workspace;

public interface WorkspaceRepository<T extends Workspace> extends CrudRepository<T, ObjectId> {

	Workspace findById(String id);

	@Query(value = "{ 'workspaceId' : ?0}")
	Workspace findByWorkspaceId(String workspaceId);

	@Query(value = "{ 'organization' : ?0}")
	List<Workspace> findByOrganization(String organization);

}
