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
@ConfigurationProperties(prefix = "ìac")
public class TerraformSettings {
    private String proxy;
    private String proxyPort;
    private String proxyUser;
    private String proxyPassword;
    @Value("${iac.cron}")
    private String cron;
    
    @Value("${iac.cloudapp.url}")
    private String terraformApiUrl;
    
	public String getTerraformApiUrl() {
		return terraformApiUrl;
	}
	public void setTerraformApiUrl(String terraformApiUrl) {
		this.terraformApiUrl = terraformApiUrl;
	}
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

    
    
}
