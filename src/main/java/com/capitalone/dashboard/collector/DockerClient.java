package com.capitalone.dashboard.collector;

import java.net.MalformedURLException;

import org.json.simple.JSONObject;
import org.springframework.web.client.RestClientException;

import com.capitalone.dashboard.misc.HygieiaException;

/**
 * Client for fetching the Terraform Cloud App data
 */
public interface DockerClient {

	JSONObject getData(String uri, String apiToken) throws MalformedURLException, RestClientException, HygieiaException;

}
