package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="tasks")
public class Task extends BaseModel{
	
	private String taskId;
	
	private Date createdAt;
	
	private Date updatedAt;

	public   class Status{
		private Date timestamp;
		
		private String state;
		
		private String Message;

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getMessage() {
			return Message;
		}

		public void setMessage(String message) {
			Message = message;
		}
		
		
		
	}
	
	private Container container;
	
	
	 


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Container getContainer() {
		return container;
	}


	public void setContainer(Container container) {
		this.container = container;
	}


	 
	
	
	
}
