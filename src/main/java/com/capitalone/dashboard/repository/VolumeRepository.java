package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Volume;

public interface VolumeRepository<T extends Volume> extends CrudRepository<T, ObjectId> {
	
	@Query(value = "{'name' : ?0}")
	public Volume findByName(String name);
 
}
