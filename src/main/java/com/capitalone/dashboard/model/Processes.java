package com.capitalone.dashboard.model;

import org.json.simple.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="processes")
public class Processes extends BaseModel{
	
	private String containerId;
	
	private JSONArray processes;
	
	private JSONArray titles;
 
	
	
	public JSONArray getProcesses() {
		return processes;
	}

	public void setProcesses(JSONArray processes) {
		this.processes = processes;
	}

	public JSONArray getTitles() {
		return titles;
	}

	public void setTitles(JSONArray titles) {
		this.titles = titles;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	 
	
	
}
