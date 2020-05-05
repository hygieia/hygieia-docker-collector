package com.capitalone.dashboard.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;

import com.capitalone.dashboard.model.CollectorItem;

public interface TerraformCollectorItemRepository extends BaseCollectorItemRepository<CollectorItem> {

	@Query(value = "{ 'id' : ?0}")
	CollectorItem findTerraformCollectorinfrastructureAsCode(ObjectId id);

	@Query(value = "{ 'collectorId' : ?0, enabled: true}")
	List<CollectorItem> findTerraformItemCollectorByCollectorItemId(ObjectId collectorItemId);

	@Query(value = "{ 'collectorId' : ?0, enabled: true}")
	List<CollectorItem> findByCollectorId(ObjectId collectorId);

	@Query(value = "{ 'collectorId' : ?0, enabled: true}")
	Integer countByCollectorId(ObjectId collectorId);

}
