package com.capitalone.dashboard.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dashboard.collector.DefaultTerraformClient;
import com.capitalone.dashboard.collector.TerraformCollectorTask;
import com.capitalone.dashboard.collector.TerraformSettings;
import com.capitalone.dashboard.model.Collector;
import com.capitalone.dashboard.model.CollectorType;
import com.capitalone.dashboard.model.ComponentData;
import com.capitalone.dashboard.model.Run;
import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.Workspace;
import com.capitalone.dashboard.repository.BaseCollectorRepository;
import com.capitalone.dashboard.repository.ComponentRepository;
import com.capitalone.dashboard.repository.RunRepository;
import com.capitalone.dashboard.repository.CollectorItemRepository;
import com.capitalone.dashboard.repository.WorkspaceRepository;
import com.capitalone.dashboard.service.TerraformServiceImpl;

@RestController
public class TerraformController {
	private static final Log LOG = LogFactory.getLog(TerraformController.class);

	private final TerraformServiceImpl terraformService;

	@Autowired
	public TerraformController(TerraformServiceImpl terraformService) {
		this.terraformService = terraformService;
	}

	@RequestMapping(value = "/collector/terraform", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getTerraformDetails() {
		LOG.debug("Call Recieved @ /collector/terraform ::");
		return ResponseEntity.ok(terraformService.getTerraformDetails());
	}

	@RequestMapping(value = "/collector/terraform/run/count", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getTerraformDetailCountRun(@RequestParam String workspace,
			@RequestParam String status, @RequestParam String timeline, @RequestParam Integer range) {
		LOG.debug("Call Recieved @ /collector/terraform/run/count :: Params - Workspace :" + workspace + ", status: " + status  + ", timeline" + timeline + ", range: " + range );
		return ResponseEntity.ok(terraformService.getTerraformDetailCountRun(workspace, status, timeline, range));
	}

	@RequestMapping(value = "/collector/terraform/run/aggregate", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getTerraformDetailAggregateRun(@RequestParam String workspace,
			@RequestParam String status, @RequestParam String timeline, @RequestParam Integer range) {
		LOG.debug("Call Recieved @ /collector/terraform/run/aggregate :: Params - Workspace :" + workspace + ", status: " + status  + ", timeline" + timeline + ", range: " + range );
		return ResponseEntity.ok(terraformService.getTerraformDetailAggregateRun(workspace, status, timeline, range));
	}

	@RequestMapping(value = "/collector/terraform/card", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ComponentData> getCardDetails() {
		LOG.debug("Call Recieved @ /collector/terraform/card ::");
		return ResponseEntity.ok(terraformService.getCardDetails());

	}
}
