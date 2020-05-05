package com.capitalone.dashboard.model;

import java.util.List;

import com.capitalone.dashboard.enums.STATUS;

public class Terraform {
	
	private STATUS status;

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}
	
	;
	
	private List<Organization> orgList;
	

	public List<Organization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}

	 
	
	
	

}
