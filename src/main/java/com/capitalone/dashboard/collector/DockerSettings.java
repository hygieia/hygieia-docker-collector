package com.capitalone.dashboard.collector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Bean to hold settings specific to the Terraform collector.
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "docker")
public class DockerSettings {
	private String proxy;
	private String proxyPort;
	private String proxyUser;
	private String proxyPassword;
	
	@Value("${docker.cron}")
	private String cron;

	@Value("${docker.api.version:v1.24}")
	private String dockerApiVersion;

	@Value("${docker.stats.uri}")
	private String dockerStatsUri;

	@Value("${docker.stats.container.consumes}")
	private String dockerStatsContainerConsumes;

	@Value("${docker.stats.container.processes}")
	private String dockerStatsContainerProcesses;

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getDockerApiVersion() {
		return dockerApiVersion;
	}

	public void setDockerApiVersion(String dockerApiVersion) {
		this.dockerApiVersion = dockerApiVersion;
	}

	public String getDockerStatsUri() {
		return dockerStatsUri;
	}

	public void setDockerStatsUri(String dockerStatsUri) {
		this.dockerStatsUri = dockerStatsUri;
	}

	public String getDockerStatsContainerConsumes() {
		return dockerStatsContainerConsumes;
	}

	public void setDockerStatsContainerConsumes(String dockerStatsContainerConsumes) {
		this.dockerStatsContainerConsumes = dockerStatsContainerConsumes;
	}

	public String getDockerStatsContainerProcesses() {
		return dockerStatsContainerProcesses;
	}

	public void setDockerStatsContainerProcesses(String dockerStatsContainerProcesses) {
		this.dockerStatsContainerProcesses = dockerStatsContainerProcesses;
	}

}
