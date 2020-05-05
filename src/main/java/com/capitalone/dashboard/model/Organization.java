package com.capitalone.dashboard.model;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*Each Terraform app has Organization > Workspace > run jobs*/
@Document(collection = "organization")
public class Organization extends BaseModel {
	
	 @Indexed
	 private ObjectId collectorItemId;
	
	private String organizationId;
	
	private String name;
	
	private Date createdAt;
	
	@org.springframework.data.annotation.Transient
	private List<Workspace> workSpaceList;
	
	public List<Workspace> getWorkSpaceList() {
		return workSpaceList;
	}

	public void setWorkSpaceList(List<Workspace> workSpaceList) {
		this.workSpaceList = workSpaceList;
	}

	public Organization() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ObjectId getCollectorItemId() {
		return collectorItemId;
	}

	public void setCollectorItemId(ObjectId collectorItemId) {
		this.collectorItemId = collectorItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
}
