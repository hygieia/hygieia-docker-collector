package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="nodes")
public class Node extends BaseModel{
	
	private String nodeId;
	
	private String[] names;
	
	
	
	
	
	
	
	public class Spec{
		private String Role;
		private String Availability;
		public String getRole() {
			return Role;
		}
		public void setRole(String role) {
			Role = role;
		}
		public String getAvailability() {
			return Availability;
		}
		public void setAvailability(String availability) {
			Availability = availability;
		}
		
		
	}
	
	public class Status{
		private String State;
		private String Addr;
		public String getState() {
			return State;
		}
		public void setState(String state) {
			State = state;
		}
		public String getAddr() {
			return Addr;
		}
		public void setAddr(String addr) {
			Addr = addr;
		}
		
		
	}
	
	public  class  ManagerStatus{
		private String Leader;
		private String Reachability;
		private String Addr;
		public String getLeader() {
			return Leader;
		}
		public void setLeader(String leader) {
			Leader = leader;
		}
		public String getReachability() {
			return Reachability;
		}
		public void setReachability(String reachability) {
			Reachability = reachability;
		}
		public String getAddr() {
			return Addr;
		}
		public void setAddr(String addr) {
			Addr = addr;
		}
		
		
	}
	
	private String image;
	
	private String ImageId;
	private Date createdat;
	private Date updatedat;
	private String state;
	private String status;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public Date getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
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
