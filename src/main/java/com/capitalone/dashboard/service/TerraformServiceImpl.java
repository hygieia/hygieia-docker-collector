package com.capitalone.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitalone.dashboard.collector.TerraformCollectorTask;
import com.capitalone.dashboard.enums.STATUS;
import com.capitalone.dashboard.model.ComponentData;
import com.capitalone.dashboard.model.Organization;
import com.capitalone.dashboard.model.Run;
import com.capitalone.dashboard.model.Series;
import com.capitalone.dashboard.model.Terraform;
import com.capitalone.dashboard.model.Workspace;
import com.capitalone.dashboard.repository.CollectorItemRepository;
import com.capitalone.dashboard.repository.OrganizationRepository;
import com.capitalone.dashboard.repository.RunRepository;
import com.capitalone.dashboard.repository.TerraformCustomRepository;
import com.capitalone.dashboard.repository.WorkspaceRepository;

@Service
public class TerraformServiceImpl implements TerraformService{
	private static final Log LOG = LogFactory.getLog(TerraformService.class);
	
	@Autowired
	OrganizationRepository<Organization> organizationRepository;

	@Autowired
	CollectorItemRepository collectorItemRepository;

	@Autowired
	WorkspaceRepository<Workspace> workspaceRepository;

	@Autowired
	RunRepository<Run> runRepository;

	@Autowired
	TerraformCustomRepository terraformCustomRepository;

	private boolean isConfigSet() {
		return collectorItemRepository.findAll().iterator().hasNext();
	}

	@Override
	public ComponentData getTerraformDetails() {
		ComponentData componentData = new ComponentData();

		Terraform terraform = new Terraform();

		if (!isConfigSet()) {
			componentData.setStatus(STATUS.NO_CONFIG);
			return componentData;
		}

		int totalOrganization = 0;

		int totalWorkspace = 0;

		int totalRun = 0;
		componentData.setStatus(STATUS.PASS);
		List<Organization> orgList = (List<Organization>) organizationRepository.findAll();

		/* Logic: Find all Organizations, Workspace & Job Runs */
		terraform.setOrgList(orgList);
		if (orgList != null && orgList.size() > 0) {
			for (Organization organization : orgList) {
				totalOrganization = 1 + totalOrganization;
				List<Workspace> workSpaceList = workspaceRepository.findByOrganization(organization.getName());
				organization.setWorkSpaceList(workSpaceList);
				if (workSpaceList != null && workSpaceList.size() > 0) {
					totalWorkspace = 1 + totalWorkspace;
					for (Workspace workspace : workSpaceList) {

						List<Run> runList = runRepository.findByWorkspaceId(workspace.getWorkspaceId());

						if (runList != null & runList.size() > 0)
							totalRun = runList.size() + totalRun;

						workspace.setRunList(runList);
						workspace.setUniqueStatus(terraformCustomRepository.findDistinctRunStatus(workspace.getWorkspaceId()));
					}
				}
			}
		}
		
		componentData.setData(terraform);
		return componentData;
	}
	
	@Override
	public ComponentData getTerraformDetailCountRun(String workspaceId, String status, String timeline, Integer range) {
		return terraformCustomRepository.getTerraformDetailCountRun(workspaceId, status, timeline, range);
	}
	
	@Override
	public ComponentData getTerraformDetailAggregateRun(String workspaceId, String status, String timeline,
			Integer range) {
		return terraformCustomRepository.getTerraformDetailAggregateRun(workspaceId, timeline, range, status);
	}
	
	@Override
	public ComponentData getCardDetails() {
		List<Series> cards = new ArrayList<Series>();
		ComponentData componentData = new ComponentData();
		List<Organization> orgList = (List<Organization>) organizationRepository.findAll();
		List<Workspace> workspaceList = (List<Workspace>) workspaceRepository.findAll();
		List<Run> runList = (List<Run>) runRepository.findAll();

		if (!isConfigSet()) {
			componentData.setStatus(STATUS.NO_CONFIG);
			return componentData;
		}

		componentData.setStatus(STATUS.PASS);

		Series orgSeries = new Series("Organizations", "0");

		Series workspaceSeries = new Series("Workspaces", "0");

		Series runSeries = new Series("Jobs", "0");

		if (orgList != null) {
			orgSeries.setValue(String.valueOf(orgList.size()));

			if (workspaceList != null) {
				workspaceSeries.setValue(String.valueOf(workspaceList.size()));

				if (runList != null) {
					runSeries.setValue(String.valueOf(runList.size()));
				}
			}
		}

		cards.add(orgSeries);
		cards.add(workspaceSeries);
		cards.add(runSeries);

		componentData.setData(cards);

		return componentData;
	}
}
