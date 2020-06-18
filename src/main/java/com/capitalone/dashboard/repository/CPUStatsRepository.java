package com.capitalone.dashboard.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capitalone.dashboard.model.CPUStats;
import com.capitalone.dashboard.model.Image;
import com.capitalone.dashboard.model.Node;

public interface CPUStatsRepository<T extends CPUStats> extends CrudRepository<T, ObjectId> {

 
}
