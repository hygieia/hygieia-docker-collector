package com.capitalone.dashboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
public class Service extends BaseModel {

	private String serviceId;

	private Date CreatedAt;
	private Date UpdatedAt;
	
	private List<Endpoint> endpoints = new ArrayList<Endpoint>();
	
	

	public List<Endpoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	public class Endpoint {
		
		
		private List<Ports> ports = new ArrayList<Ports>();
		
		
		private List<VirtualIPs> virtualIPs = new ArrayList<VirtualIPs>();
		
		
 
		public List<Ports> getPorts() {
			return ports;
		}

		public void setPorts(List<Ports> ports) {
			this.ports = ports;
		}

		public List<VirtualIPs> getVirtualIPs() {
			return virtualIPs;
		}

		public void setVirtualIPs(List<VirtualIPs> virtualIPs) {
			this.virtualIPs = virtualIPs;
		}

		public class Ports {
			private String protocol;

			private String targetPort;

			private String publishedPort;

			public String getProtocol() {
				return protocol;
			}

			public void setProtocol(String protocol) {
				this.protocol = protocol;
			}

			public String getTargetPort() {
				return targetPort;
			}

			public void setTargetPort(String targetPort) {
				this.targetPort = targetPort;
			}

			public String getPublishedPort() {
				return publishedPort;
			}

			public void setPublishedPort(String publishedPort) {
				this.publishedPort = publishedPort;
			}

		}

		public class VirtualIPs {
			private String networkID;

			private String addr;

			public String getNetworkID() {
				return networkID;
			}

			public void setNetworkID(String networkID) {
				this.networkID = networkID;
			}

			public String getAddr() {
				return addr;
			}

			public void setAddr(String addr) {
				this.addr = addr;
			}

		}

	}

}
