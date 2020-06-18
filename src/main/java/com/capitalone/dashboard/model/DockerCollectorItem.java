package com.capitalone.dashboard.model;

public class DockerCollectorItem extends CollectorItem{
	
	public static final String API_TOKEN = "apiToken"; // terraform app api token allowed
	
	public static final String INSTANCE_URL="instanceUrl";
	
	public static final String INSTANCE_PORT="instancePort";
	
	public static String getApiToken() {
		return API_TOKEN;
	}

	public static String getInstanceUrl() {
		return INSTANCE_URL;
	}

	public static String getInstancePort() {
		return INSTANCE_PORT;
	}
	
}
