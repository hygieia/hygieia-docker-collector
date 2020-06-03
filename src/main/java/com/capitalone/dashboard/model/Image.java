package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="networks")
public class Image extends BaseModel{
	
	private String containerId;
	
	private String[] names;
	
	private String image;
	
	private String ImageId;
	private Date created;
	private String state;
	private String status;
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageId() {
		return ImageId;
	}
	public void setImageId(String imageId) {
		ImageId = imageId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
