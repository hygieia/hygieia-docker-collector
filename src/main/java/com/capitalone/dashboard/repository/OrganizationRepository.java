package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.Organization;
import com.capitalone.dashboard.model.Workspace;

public interface OrganizationRepository<T extends Organization> extends CrudRepository<T, ObjectId> {

	Organization findById(String id);

	Organization findByOrganizationId(String id);

	@Query(value = "{ 'collectorItemId' : ?0}")
	List<Organization> findByCollectorItemId(ObjectId collectorItemId);

}
