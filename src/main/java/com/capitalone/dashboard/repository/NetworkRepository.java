package com.capitalone.dashboard.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.Network;
import com.capitalone.dashboard.model.Node;

public interface NetworkRepository<T extends Network> extends CrudRepository<T, ObjectId> {

	@Query(value = "{'networkId': ?0}")
	public Network findByNetworkId(String networkId);

}
