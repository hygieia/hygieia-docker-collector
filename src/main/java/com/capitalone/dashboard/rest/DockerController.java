package com.capitalone.dashboard.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dashboard.model.ComponentData;
import com.capitalone.dashboard.service.DockerServiceImpl;

@RestController
public class DockerController {
	private static final Log LOG = LogFactory.getLog(DockerController.class);

	private final DockerServiceImpl dockerService;

	@Autowired
	public DockerController(DockerServiceImpl dockerService) {
		this.dockerService = dockerService;
	}

	@RequestMapping(value = "/collector/docker/meta/count", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getDockerMetaCount() {
		LOG.debug("Call Recieved @ //collector/docker/meta/count :: ");
		return ResponseEntity.ok(dockerService.getDockerMetaCount());
	}
	
	@RequestMapping(value = "/collector/docker/meta/data", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getDockerMetaData() {
		LOG.debug("Call Recieved @ /collector/docker/meta/data :: ");
		return ResponseEntity.ok(dockerService.getDockerMetaData());
	}
	
	 
	@RequestMapping(value = "/collector/docker/cpu/stats", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getDockerCpuStats() {
		LOG.debug("Call Recieved @ /collector/docker/cpu/stats :: ");
		return ResponseEntity.ok(dockerService.getDockerCpuStats());
	}

	
}
