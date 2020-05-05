package com.capitalone.dashboard.model;

public class TerraformCollectorItem extends CollectorItem{
	
	public static final String API_TOKEN = "apiToken"; // terraform app api token allowed
	
	public static String getApiToken() {
		return API_TOKEN;
	}
}
