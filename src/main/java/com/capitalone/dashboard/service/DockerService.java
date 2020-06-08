package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.ComponentData;

public interface DockerService {
	
	public ComponentData getDockerMetaCount();
	
	public ComponentData getDockerMetaAggregate(String meta, String status, String timeline, Integer range);
	
	public ComponentData getDockerMetaData();
	
	public ComponentData getContainerProcessesTopRoute(String containerId);
	
	public ComponentData getDockerCpuStats(); 

}
