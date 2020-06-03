package com.capitalone.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.capitalone.dashboard.collector.DockerSettings;
import com.capitalone.dashboard.model.CPUStats;
import com.capitalone.dashboard.model.ComponentData;
import com.capitalone.dashboard.model.Container;
import com.capitalone.dashboard.model.Image;
import com.capitalone.dashboard.model.Network;
import com.capitalone.dashboard.model.Node;
import com.capitalone.dashboard.model.Processes;
import com.capitalone.dashboard.model.Series;
import com.capitalone.dashboard.model.Task;
import com.capitalone.dashboard.model.Volume;
import com.capitalone.dashboard.repository.CPUStatsRepository;
import com.capitalone.dashboard.repository.ContainerRepository;
import com.capitalone.dashboard.repository.DockerCustomRepository;
import com.capitalone.dashboard.repository.ImageRepository;
import com.capitalone.dashboard.repository.NetworkRepository;
import com.capitalone.dashboard.repository.NodeRepository;
import com.capitalone.dashboard.repository.ProcessesRepository;
import com.capitalone.dashboard.repository.TaskRepository;
import com.capitalone.dashboard.repository.VolumeRepository;

@Service
public class DockerServiceImpl implements DockerService {
	private static final Log LOG = LogFactory.getLog(DockerService.class);

	@Autowired
	NetworkRepository<Network> networkRepository;

	@Autowired
	NodeRepository<Node> nodeRepository;

	@Autowired
	ContainerRepository<Container> containerRepository;

	@Autowired
	ImageRepository<Image> imageRepository;

	@Autowired
	ProcessesRepository<Processes> processesRepository;

	@Autowired
	TaskRepository<Task> taskRepository;
	
	@Autowired
	VolumeRepository<Volume> volumeRepository;
	
	@Autowired
	CPUStatsRepository<CPUStats> cpuStatsRepository;

	@Autowired
	DockerCustomRepository terraformCustomRepository;
	
	@Autowired
	DockerSettings dockerSettings;

	private boolean isConfigSet() {
		// return collectorItemRepository.findAll().iterator().hasNext();
		return false;
	}

 
	public ComponentData getDockerMetaCount() {
		// TODO Auto-generated method stub
		CrudRepository[] obj = {networkRepository, nodeRepository, containerRepository, imageRepository, processesRepository, taskRepository };
		String[] names = {"Network", "Node", "Container", "Image", "Processes", "Task" };
		int i =0;
		ComponentData componentData = new ComponentData();
		List<Series>  data = new ArrayList<Series>();
		for(CrudRepository o : obj ) {
			Series series = new Series();
			series.setName(names[i++]);
			series.setValue(o.count());
			
			data.add(series);
		}
		
		componentData.setData(data);
		return componentData;
	}


	public ComponentData getDockerMetaAggregate(String meta, String status, String timeline, Integer range) {
		ComponentData componentData = new ComponentData();
		
		return componentData;
	}


	public ComponentData getDockerMetaData() {
		ComponentData componentData = new ComponentData();
		String[] uris
		  = dockerSettings.getDockerStatsUri().split(","); 
		JSONObject data = new JSONObject();
		
		for (String uri : uris) {
		switch (uri.toUpperCase()) { 
				case "VOLUMES": 
					 List<Volume> volumes  = (List<Volume>) volumeRepository.findAll();
					 data.put("volumes", volumes);
					break;
				 
				 case "NETWORKS": 
					 List<Network> networks  = (List<Network>) networkRepository.findAll();
					 data.put("networks", networks); 
					 break;
				 
				 case "NODES": 
					 List<Node> nodes  = (List<Node>) nodeRepository.findAll();
					 data.put("nodes", nodes);
					 break;
				 
				 case "TASKS": 
					 List<Task> tasks  = (List<Task>) taskRepository.findAll();
					 data.put("tasks", tasks);
					 break;
					 
				 case "SERVICES": 
					 break;
					 
				 case "CONTAINERS": 
					 List<Container> containers  = (List<Container>) containerRepository.findAll();
				 data.put("containers", containers);
				 break;
				 
				 default: break; }
		
		}
		
		 List<Processes> processes  = (List<Processes>) processesRepository.findAll();
		 data.put("processes", processes);
		
		componentData.setData(data);
		return componentData;
	}


	public ComponentData getContainerProcessesTopRoute(String containerId) {
		ComponentData componentData = new ComponentData();
		
		return componentData;
	}


	public ComponentData getDockerCpuStats() {
		ComponentData componentData = new ComponentData();
		List<CPUStats> cpuStats = (List<CPUStats>)cpuStatsRepository.findAll();
		List<Series> data = new ArrayList<Series>();
		if(cpuStats != null && cpuStats.size() > 0) {
		CPUStats stats = cpuStats.get(0);
		
		Series systemCpuUsage = new Series();
		systemCpuUsage.setName("System Usage");
		systemCpuUsage.setValue(stats.getStatsUsage().getSystemCpuUsage());
		
		data.add(systemCpuUsage);
		
		
		Series memoryStatsUsage = new Series();
		memoryStatsUsage.setName("Memory Usage");
		memoryStatsUsage.setValue(stats.getMemoryStats().getUsage());
		data.add(memoryStatsUsage);
		
		
		
		Series memoryStatsMaxUsage = new Series();
		memoryStatsMaxUsage.setName("Memory Max Usage");
		memoryStatsMaxUsage.setValue(stats.getMemoryStats().getMaxUsage());
		data.add(memoryStatsMaxUsage);
		
		
		
		Series memoryStatsLimit = new Series();
		memoryStatsLimit.setName("Memory Limit");
		memoryStatsLimit.setValue(stats.getMemoryStats().getLimit());
		data.add(memoryStatsLimit);
		
		}
		
		
		componentData.setData(data);
		return componentData;
	}
	
	
}
