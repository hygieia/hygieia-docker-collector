package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="images")
public class Image extends BaseModel{
	
	
	private String imageId;
	private Long created;
	private String state;
	private Long size;
	private Long virtualSize;
	 
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getVirtualSize() {
		return virtualSize;
	}
	public void setVirtualSize(Long virtualSize) {
		this.virtualSize = virtualSize;
	}
	 
	
	
	
	
	
	
}
