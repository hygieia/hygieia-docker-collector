package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="networks")
public class Network extends BaseModel{
	
	private String networkId;
	
	
	private String name;
	
	private Date created;
	private String scope;
	private String Driver;
	
	private Boolean attachable;
	
	private Boolean ingress;
	
	
	
	public Boolean getAttachable() {
		return attachable;
	}
	public void setAttachable(Boolean attachable) {
		this.attachable = attachable;
	}
	public Boolean getIngress() {
		return ingress;
	}
	public void setIngress(Boolean ingress) {
		this.ingress = ingress;
	}
	public String getNetworkId() {
		return networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	
	
	
}
