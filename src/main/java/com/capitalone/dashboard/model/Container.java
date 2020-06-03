package com.capitalone.dashboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "containers")
public class Container extends BaseModel {

	private String containerId;

	private String[] names;

	private String image;

	private String ImageId;
	private Date created;
	private String state;
	private String status;

	/* private Network networks; */
	
	private Bridge bridge;
	
	private List<Mount> mounts = new ArrayList<Container.Mount>();
	
	public List<Mount> getMounts() {
		return mounts;
	}

	public void setMounts(List<Mount> mounts) {
		this.mounts = mounts;
	}

	public Bridge getBridge() {
		return bridge;
	}

	public void setBridge(Bridge bridge) {
		this.bridge = bridge;
	}

	 
	
	private Processes processes;
	
	public Processes getProcesses() {
		return processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

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

	/*
	 * public Network getNetworks() { return networks; }
	 * 
	 * public void setNetworks(Network networks) { this.networks = networks; }
	 */

 
	
	
	public class Bridge {
		
		private String IPAMConfig;
		private String Links;
		private String NetworkID;
		private String Gateway;
		private String IPAddress;
		private String IPPrefixLen;
		private String IPv6Gateway;
		private String MacAddress;
		public String getIPAMConfig() {
			return IPAMConfig;
		}
		public void setIPAMConfig(String iPAMConfig) {
			IPAMConfig = iPAMConfig;
		}
		public String getLinks() {
			return Links;
		}
		public void setLinks(String links) {
			Links = links;
		}
		public String getNetworkID() {
			return NetworkID;
		}
		public void setNetworkID(String networkID) {
			NetworkID = networkID;
		}
		public String getGateway() {
			return Gateway;
		}
		public void setGateway(String gateway) {
			Gateway = gateway;
		}
		public String getIPAddress() {
			return IPAddress;
		}
		public void setIPAddress(String iPAddress) {
			IPAddress = iPAddress;
		}
		public String getIPPrefixLen() {
			return IPPrefixLen;
		}
		public void setIPPrefixLen(String iPPrefixLen) {
			IPPrefixLen = iPPrefixLen;
		}
		public String getIPv6Gateway() {
			return IPv6Gateway;
		}
		public void setIPv6Gateway(String iPv6Gateway) {
			IPv6Gateway = iPv6Gateway;
		}
		public String getMacAddress() {
			return MacAddress;
		}
		public void setMacAddress(String macAddress) {
			MacAddress = macAddress;
		}
		
		
		
	
	}
	
	public class Mount {

		private String type;
		private String name;
		private String source;
		private String destination;
		private String driver;
		private String mode;
		private String rw;
		private String propagation;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		public String getDriver() {
			return driver;
		}
		public void setDriver(String driver) {
			this.driver = driver;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
		public String getRw() {
			return rw;
		}
		public void setRw(String rw) {
			this.rw = rw;
		}
		public String getPropagation() {
			return propagation;
		}
		public void setPropagation(String propagation) {
			this.propagation = propagation;
		}
		
		
		
		
	}

}
