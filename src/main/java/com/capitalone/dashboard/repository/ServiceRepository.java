package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Service;

public interface ServiceRepository<T extends Service> extends CrudRepository<T, ObjectId> {

	@Query(value = "{'serviceId': ?0}")
	public Service findByServiceId(String serviceId);

}
