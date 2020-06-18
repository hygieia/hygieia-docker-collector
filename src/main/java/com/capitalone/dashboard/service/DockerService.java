package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.ComponentData;

 public interface DockerService {
	
	 ComponentData getDockerMetaCount();
	
	 ComponentData getDockerMetaData();
	
	 ComponentData getDockerCpuStats(); 

}
