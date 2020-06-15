package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.ComponentData;

 public interface DockerService {
	
	 ComponentData getDockerMetaCount();
	
	 ComponentData getDockerMetaAggregate(String meta, String status, String timeline, Integer range);
	
	 ComponentData getDockerMetaData();
	
	 ComponentData getContainerProcessesTopRoute(String containerId);
	
	 ComponentData getDockerCpuStats(); 

}
