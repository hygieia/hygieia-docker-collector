package com.capitalone.dashboard.collector;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.capitalone.dashboard.misc.HygieiaException;
import com.capitalone.dashboard.model.Collector;
import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.CollectorType;
import com.capitalone.dashboard.model.Component;
import com.capitalone.dashboard.model.Organization;
import com.capitalone.dashboard.model.Run;
import com.capitalone.dashboard.model.Workspace;
import com.capitalone.dashboard.repository.BaseCollectorRepository;
import com.capitalone.dashboard.repository.CollectorItemRepository;
import com.capitalone.dashboard.repository.ComponentRepository;
import com.capitalone.dashboard.repository.OrganizationRepository;
import com.capitalone.dashboard.repository.RunRepository;
import com.capitalone.dashboard.repository.TerraformCollectorItemRepository;
import com.capitalone.dashboard.repository.WorkspaceRepository;

@RunWith(MockitoJUnitRunner.class)
public class TerrafomCollectorTaskTest {
	  
	  @Mock private TerraformCollectorTask terraformCollectorTask;
	  @Mock private TerraformCollectorItemRepository terraformCollectoritemRepository;
	  @Mock private CollectorItemRepository collectoritemRepository;
	  @Mock private RunRepository<Run> runRepository;
	  @Mock private DefaultTerraformClient terraformClient;
	  @Mock private ComponentRepository dbComponentRepository;
	  @Mock private BaseCollectorRepository<Collector> baseCollectorRepository;
	  @Mock private WorkspaceRepository<Workspace> workspaceRepository;
	  @Mock private OrganizationRepository<Organization> organizationRepository;
	  
	  @Mock private Collector terraformCollectorType;
	  @Mock private CollectorItem terraformItem1;
	  
	  @Mock private Organization organization;
	  
	  private final String TERRAFORM_API_URL = "http://app.terraform.io/api/v2";

	  private final String ORGANIZATION_URI = "/organizations";

	  private final String WORKSPACE_URI = "/workspaces";
		
	  private final String RUN_URI = "/runs";
	  
	  @Test
	    public void collect_testCollect() throws MalformedURLException, HygieiaException {
		  
		  
		  
		  when(dbComponentRepository.findAll()).thenReturn(components());
		  
		  	Collector collector = new Collector();
	        collector.setEnabled(true);
	        collector.setName("Terraform");
	        collector.setId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        List<ObjectId> collections = new ArrayList<ObjectId>();
	        collections.add(collector.getId());
	        
	        when(baseCollectorRepository.findOne(collector.getId())).thenReturn(getTerraformCollector());

	        Set<ObjectId> iacID = new HashSet<>();
	        iacID.add(new ObjectId("111ca42a258ad365fbb64ecc"));
	        when(terraformCollectoritemRepository.findByCollectorIdIn(iacID)).thenReturn(getTerraformItems());
	       	
	        when(organizationRepository.findByCollectorItemId(new ObjectId("111ca42a258ad365fbb64ecc"))).thenReturn(null);
	        
	        
	        
	        //verify that collectorType is enabled and equal
	        assertEquals("Terraform", terraformCollectorType.getName());
	        assertEquals(true, terraformCollectorType.isEnabled());
	        
	        //verify that collectorType is enabled and equal
	        assertEquals("iac-tfci1", terraformItem1.getNiceName());
	        assertEquals(true,  terraformItem1.isEnabled());
	        
	        terraformCollectorTask.collect(terraformCollectorType);
	        
	  }
	  
	  private List<Organization> getOrganization(){
		  List<Organization> organizations = new ArrayList<Organization>();
		   organization = new Organization();
				organization.setCollectorItemId(new ObjectId("1c1ca42a258ad365fbb64ecc"));
				organization.setName("TestOrg");
				organization.setOrganizationId("test-org-id");
				
		  organizations.add(organization);
		  return organizations;
	  }
	  private Collector getTerraformCollector(){
		    terraformCollectorType = new Collector();
		    terraformCollectorType.setName("Terraform");
		    terraformCollectorType.setCollectorType(CollectorType.InfrastructureAsCode);
		    terraformCollectorType.setOnline(true);
		    terraformCollectorType.setEnabled(true);
			
			return terraformCollectorType;
	  }
	 
	   private ArrayList<CollectorItem> getTerraformItems() {
	        ArrayList<CollectorItem> collectorItems = new ArrayList<CollectorItem>();

	        terraformItem1 = new CollectorItem();
	        terraformItem1.setEnabled(true);
	        terraformItem1.setId(new ObjectId("1c1ca42a258ad365fbb64ecc"));
	        terraformItem1.setCollectorId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        terraformItem1.setNiceName("iac-tfci1");
	        terraformItem1.getOptions().put("apiToken", "7Z4VJLmzWTB2ew.atlasv1.d3I90rBfOOV0ruG3zY1zW5ctbTqsFhNyTHPRBoSwzk3qxJ9wkcBwFPaLEqvW5qPChR0");
	        
	        collectorItems.add(terraformItem1);

	        return collectorItems;
	    }

	    private ArrayList<com.capitalone.dashboard.model.Component> components() {
	        ArrayList<com.capitalone.dashboard.model.Component> cArray = new ArrayList<com.capitalone.dashboard.model.Component>();
	        com.capitalone.dashboard.model.Component c = new Component();
	        c.setId(new ObjectId());
	        c.setName("COMPONENT1");
	        c.setOwner("PrC");

	        CollectorType iacType = CollectorType.InfrastructureAsCode;
	        CollectorItem ci1 = new CollectorItem();
	        ci1.setId(new ObjectId("1c1ca42a258ad365fbb64ecc"));
	        ci1.setNiceName("ci1");
	        ci1.setEnabled(true);
	        ci1.setPushed(false);
	        ci1.setCollectorId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        c.addCollectorItem(iacType, ci1);

	        CollectorItem ci2 = new CollectorItem();
	        ci2.setId(new ObjectId("1c2ca42a258ad365fbb64ecc"));
	        ci2.setNiceName("ci2");
	        ci2.setEnabled(true);
	        ci2.setPushed(false);
	        ci2.setCollectorId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        c.addCollectorItem(iacType, ci2);

	        CollectorItem ci3 = new CollectorItem();
	        ci3.setId(new ObjectId("1c3ca42a258ad365fbb64ecc"));
	        ci3.setNiceName("ci3");
	        ci3.setEnabled(true);
	        ci3.setPushed(false);
	        ci3.setCollectorId(new ObjectId("222ca42a258ad365fbb64ecc"));
	        c.addCollectorItem(iacType, ci3);

	        cArray.add(c);

	        return cArray;
	    
	  }
}