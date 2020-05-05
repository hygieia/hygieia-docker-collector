package com.capitalone.dashboard.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/*Each Terraform app has Organization > Workspace > run jobs*/
@Document(collection = "workspace")
public class Workspace extends BaseModel {
	
	private String organization;
	
	private String workspaceId;
	
	private String Name;
	
	private Date createdAt;
	
	@Transient
	private List<Run> runList;
	
	@Transient
	private JSONObject uniqueStatus;
	
	public List<Run> getRunList() {
		return runList;
	}



	public void setRunList(List<Run> runList) {
		this.runList = runList;
	}



	public Workspace() {
		// TODO Auto-generated constructor stub
	}



	public String getWorkspaceId() {
		return workspaceId;
	}



	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}



	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}




	public String getOrganization() {
		return organization;
	}



	public void setOrganization(String organization) {
		this.organization = organization;
	}



	public JSONObject getUniqueStatus() {
		return uniqueStatus;
	}



	public void setUniqueStatus(JSONObject uniqueStatus) {
		this.uniqueStatus = uniqueStatus;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	

	 
	 



	 

	 
	
	
}
