package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "volumes")
public class Volume extends BaseModel {

	private Date CreatedAt;
	private String Driver;
	private String mountpoint;
	private String name;
	private String scope;
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	public String getMountpoint() {
		return mountpoint;
	}
	public void setMountpoint(String mountpoint) {
		this.mountpoint = mountpoint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	
}
