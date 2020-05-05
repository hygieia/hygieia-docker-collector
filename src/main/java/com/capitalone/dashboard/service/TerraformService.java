package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.ComponentData;

public interface TerraformService {

	public ComponentData getTerraformDetails();

	public ComponentData getTerraformDetailCountRun(String workspaceId, String status, String timeline, Integer range);

	public ComponentData getTerraformDetailAggregateRun(String workspaceId, String status, String timeline,Integer range);

	public ComponentData getCardDetails();

}
