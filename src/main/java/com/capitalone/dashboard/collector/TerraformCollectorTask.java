
package com.capitalone.dashboard.collector;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.capitalone.dashboard.misc.HygieiaException;
import com.capitalone.dashboard.model.CollectionError;
import com.capitalone.dashboard.model.Collector;
import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.CollectorType;
import com.capitalone.dashboard.model.Organization;
import com.capitalone.dashboard.model.Run;
import com.capitalone.dashboard.model.TerraformCollectorItem;
import com.capitalone.dashboard.model.Workspace;
import com.capitalone.dashboard.repository.BaseCollectorRepository;
import com.capitalone.dashboard.repository.CollectorItemRepository;
import com.capitalone.dashboard.repository.ComponentRepository;
import com.capitalone.dashboard.repository.OrganizationRepository;
import com.capitalone.dashboard.repository.RunRepository;
import com.capitalone.dashboard.repository.TerraformCollectorItemRepository;
import com.capitalone.dashboard.repository.WorkspaceRepository;

// Import Static
import static com.capitalone.dashboard.model.TerraformCollectorItem.API_TOKEN;;

/**
 * CollectorTask that fetches Terraform(Organization, workspace, run) details  from Terrafrom Cloud
 */
@Component
public class TerraformCollectorTask extends CollectorTask<Collector> {
	private static final Log LOG = LogFactory.getLog(TerraformCollectorTask.class);

	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
		    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	private final TerraformCollectorItemRepository terraformCollectoritemRepository;
	private final CollectorItemRepository collectoritemRepository;
	private final RunRepository<Run> runRepository;
	private final DefaultTerraformClient terraformClient;
	private final ComponentRepository dbComponentRepository;
	private final BaseCollectorRepository<Collector> baseCollectorRepository;
	private final WorkspaceRepository<Workspace> workspaceRepository;
	private final OrganizationRepository<Organization> organizationRepository;
	private final TerraformSettings terraformSetting;
	
	 
	
	private final String ORGANIZATION_URI = "/organizations";

	private final String WORKSPACE_URI = "/workspaces";
	
	private final String RUN_URI = "/runs";
	
	ResponseEntity<String> responseJSON;
	
	JSONParser jsonParser = new JSONParser();
	@Autowired
	public TerraformCollectorTask(TaskScheduler taskScheduler,
			BaseCollectorRepository<Collector> baseCollectorRepository,
			TerraformCollectorItemRepository terraformCollectoritemRepository, RunRepository runRepository,
			DefaultTerraformClient terraformClient, TerraformSettings terraformSetting,
			ComponentRepository dbComponentRepository,
			OrganizationRepository organizationRepository,
			CollectorItemRepository collectoritemRepository,
			WorkspaceRepository workspaceRepository) {
		super(taskScheduler, "Terraform");
		this.baseCollectorRepository = baseCollectorRepository;
		this.terraformCollectoritemRepository = terraformCollectoritemRepository;
		this.runRepository = runRepository;
		this.terraformClient = terraformClient;
		this.dbComponentRepository = dbComponentRepository;
		this.workspaceRepository = workspaceRepository;
		this.organizationRepository = organizationRepository;
		this.collectoritemRepository = collectoritemRepository;
		this.terraformSetting = terraformSetting;
	}

	@Override
	public Collector getCollector() {
		Collector protoType = new Collector();
		protoType.setName("Terraform");
		protoType.setCollectorType(CollectorType.InfrastructureAsCode);
		protoType.setOnline(true);
		protoType.setEnabled(true);
		
		Map<String, Object> allOptions = new HashMap<>();
	        allOptions.put(API_TOKEN, "");
	        protoType.setAllFields(allOptions);

        Map<String, Object> uniqueOptions = new HashMap<>();
        uniqueOptions.put(API_TOKEN, "");
        protoType.setUniqueFields(uniqueOptions);
		 
		return protoType;
	}

	@Override
	public BaseCollectorRepository<Collector> getCollectorRepository() {
		return baseCollectorRepository;
	}

	@Override
	public String getCron() {
		
		return terraformSetting.getCron();
	}

	@Override
	public void collect(Collector collector) {
		
		
		/**
		 * Logic: Get all the collector items from the collector_item collection for
		 * this collector. Find the organization, Workspace and run details. Save or
		 * update in DB In case of any error add it to collection error of collector
		 * item
		 */
		List<ObjectId> objectIds = new ArrayList<ObjectId>();
		objectIds.add(collector.getId());
		
		List<CollectorItem> terraformCollectorItems = (List<CollectorItem>) collectoritemRepository.findByCollectorIdIn(objectIds);

		for (CollectorItem terraformCollectorItem : terraformCollectorItems) {

			String apiToken = terraformCollectorItem.getOptions().get("apiToken").toString().trim();
			String urlStr = null;

			try {
				urlStr = terraformSetting.getTerraformApiUrl() + ORGANIZATION_URI;
				LOG.debug("Making API Call (Organization) @ " + urlStr);
				JSONObject organizationsObject = (JSONObject) terraformClient.getData(urlStr, apiToken);
				LOG.debug("Recieved API Call Data (Organization) @ " + organizationsObject);

				if (organizationsObject != null) {

					JSONArray organizationArray = (JSONArray) organizationsObject.get("data");

					for (int k = 0; k < organizationArray.size(); k++) {

						JSONObject organizationObject = (JSONObject) jsonParser
								.parse(organizationArray.get(k).toString());

						String orgId = saveOrganizationIfNotExists(terraformCollectorItem.getId(), organizationObject);

						urlStr = terraformSetting.getTerraformApiUrl()  + ORGANIZATION_URI + "/" + orgId + WORKSPACE_URI;
						LOG.debug("Making API Call (Workspace) @ " + urlStr);
						JSONObject workspacesObject = (JSONObject) terraformClient.getData(urlStr, apiToken);
						LOG.debug("Recieved API Call Data (Workspace) @ " + workspacesObject);

						if (workspacesObject != null) {
							/*
							 * Read the Workspaces and update
							 */
							JSONArray workspacesArray = (JSONArray) workspacesObject.get("data");
							for (int i = 0; i < workspacesArray.size(); i++) {
								JSONObject workspaceObject = (JSONObject) jsonParser
										.parse(workspacesArray.get(i).toString());

								String wsId = saveWorkspaceIfNotExists(orgId, workspaceObject);

								JSONObject runsObject = (JSONObject) terraformClient
										.getData(terraformSetting.getTerraformApiUrl()  + WORKSPACE_URI + "/" + wsId + RUN_URI, apiToken);
								if (runsObject != null) {
									/*
									 * Read the runs and update
									 */
									JSONArray runsArray = (JSONArray) runsObject.get("data");

									for (i = 0; i < runsArray.size(); i++) {
										JSONObject runObject = (JSONObject) jsonParser
												.parse(runsArray.get(i).toString());

										String runId = saveRunIfNotExists(wsId, runObject);
									}
								}
							}
						}

					}

				}

			} catch (ParseException pe) {
				LOG.error("Parse Exception encountered while processing data for:" + urlStr, pe);

			} catch (MalformedURLException malE) {
				LOG.error("Error fetching details for:" + urlStr, malE);
				CollectionError error = new CollectionError(CollectionError.UNKNOWN_HOST, urlStr);
				terraformCollectorItem.getErrors().add(error);

			} catch (RestClientException restE) {
				LOG.error("Error fetching commits for:" + urlStr, restE);
				CollectionError error = new CollectionError(CollectionError.UNKNOWN_HOST, urlStr);
				terraformCollectorItem.getErrors().add(error);
			} catch (HygieiaException hygieiaE) {
				LOG.error("Error fetching commits for:" + urlStr, hygieiaE);
				CollectionError error = new CollectionError(CollectionError.UNKNOWN_HOST, urlStr);
				terraformCollectorItem.getErrors().add(error);
			}
			collectoritemRepository.save(terraformCollectorItem);
		}
	}
	
	private String saveOrganizationIfNotExists(ObjectId collectorItemId, JSONObject organizationObject) {
		// TODO Auto-generated method stub
		
		String Id =(String) organizationObject.get("id");
		String name =(String) ( (JSONObject) organizationObject.get("attributes")).get("name");
		String createdAt =(String) ( (JSONObject) organizationObject.get("attributes")).get("created-at");
		try {
			Organization organization = organizationRepository.findByOrganizationId(Id);
			if( organization == null)
				organization = new Organization();
				organization.setCollectorItemId(collectorItemId);
				organization.setOrganizationId(Id);
				organization.setName(name);
				
				organization.setCreatedAt(dateFormat.parse(createdAt));
				
				organizationRepository.save(organization);
			
			
			return Id;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			LOG.error("Error saving Organization:" + e);
			return null;
		}
	}

	private String saveWorkspaceIfNotExists(String organization, JSONObject workspaceObject) {
		// TODO Auto-generated method stub
		
		String Id =(String) workspaceObject.get("id");
		String name =(String) ( (JSONObject) workspaceObject.get("attributes")).get("name");
		String createdAt =(String) ( (JSONObject) workspaceObject.get("attributes")).get("created-at");
		try {
			Workspace workspace = workspaceRepository.findByWorkspaceId(Id);
			if( workspace == null)
				workspace = new Workspace();
				workspace.setWorkspaceId(Id);
				workspace.setName(name);
				workspace.setOrganization(organization);
					
				workspace.setCreatedAt(dateFormat.parse(createdAt));
				
				workspaceRepository.save(workspace);
		
			
			return Id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Error saving Workspace:" + e);
			return null;
		}
	}
	
	private String saveRunIfNotExists(String workspaceId, JSONObject runObject){
		// TODO Auto-generated method stub
		String Id =(String) runObject.get("id");
		String status =(String) ( (JSONObject) runObject.get("attributes")).get("status");
		String createdAt =(String) ( (JSONObject) runObject.get("attributes")).get("created-at");
		
		try {
			Run run = runRepository.findByRunId(Id);
			
			if( run == null)
				run = new Run();
				run.setRunId(Id);
				run.setWorkspaceId(workspaceId);
				run.setStatus(status); 
				
				run.setCreatedAt(dateFormat.parse(createdAt));
				
				runRepository.save(run);
			
			
			return Id;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			
			LOG.error("Error saving Run:" + e);
			return null;
		}
	}
	
}
