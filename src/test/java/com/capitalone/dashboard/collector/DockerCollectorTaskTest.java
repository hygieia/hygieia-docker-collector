package com.capitalone.dashboard.collector;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capitalone.dashboard.model.CPUStats;
import com.capitalone.dashboard.model.Collector;
import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.CollectorType;
import com.capitalone.dashboard.model.Component;
import com.capitalone.dashboard.model.Container;
import com.capitalone.dashboard.model.Image;
import com.capitalone.dashboard.model.Network;
import com.capitalone.dashboard.model.Node;
import com.capitalone.dashboard.model.Processes;
import com.capitalone.dashboard.model.Task;
import com.capitalone.dashboard.model.Volume;
import com.capitalone.dashboard.repository.CPUStatsRepository;
import com.capitalone.dashboard.repository.CollectorItemRepository;
import com.capitalone.dashboard.repository.CollectorRepository;
import com.capitalone.dashboard.repository.ComponentRepository;
import com.capitalone.dashboard.repository.ContainerRepository;
import com.capitalone.dashboard.repository.ImageRepository;
import com.capitalone.dashboard.repository.NetworkRepository;
import com.capitalone.dashboard.repository.NodeRepository;
import com.capitalone.dashboard.repository.ProcessesRepository;
import com.capitalone.dashboard.repository.TaskRepository;
import com.capitalone.dashboard.repository.VolumeRepository;

@RunWith(MockitoJUnitRunner.class)
public class DockerCollectorTaskTest {
	
	@Mock
	NetworkRepository<Network> networkRepository;

	@Mock
	NodeRepository<Node> nodeRepository;

	@Mock
	ContainerRepository<Container> containerRepository;

	@Mock
	ImageRepository<Image> imageRepository;

	@Mock
	ProcessesRepository<Processes> processesRepository;

	@Mock
	TaskRepository<Task> taskRepository;

	@Mock
	VolumeRepository<Volume> volumeRepository;

	@Mock
	CPUStatsRepository<CPUStats> cpuStatsRepository;

	@Mock
	DockerSettings dockerSettings;
	
	
	@InjectMocks
	DockerCollectorTask dockerCollectorTask;
	
	@Mock
	CollectorItemRepository collectorItemRepository; 
	
	@Mock private CollectorItem dockerItem1;
	  
	@Mock
	CollectorRepository collectorRepository; 
	
	@Mock
	ComponentRepository dbComponentRepository;
	
	  @Mock private Collector dockerCollectorType;
	
	@Test
	public void testDockerDaemonsPressence() {
		  when(dbComponentRepository.findAll()).thenReturn(components());
		  
		  	Collector collector = new Collector();
	        collector.setEnabled(true);
	        collector.setName("Docker");
	        collector.setId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        List<ObjectId> collections = new ArrayList<ObjectId>();
	        collections.add(collector.getId());
	        
	        when(collectorRepository.findOne(collector.getId())).thenReturn(getDockerCollector());

	        Set<ObjectId> dockerID = new HashSet<>();
	        dockerID.add(new ObjectId("111ca42a258ad365fbb64ecc"));
	        when(collectorItemRepository.findByCollectorIdIn(dockerID)).thenReturn(getDockerItems());
	       	
	        
	        
	        
	        //verify that collectorType is enabled and equal
	        assertEquals("Docker", dockerCollectorType.getName());
	        assertEquals(true, dockerCollectorType.isEnabled());
	        
	        //verify that collectorType is enabled and equal
	        assertEquals("docker-tfci1", dockerItem1.getNiceName());
	        assertEquals(true,  dockerItem1.isEnabled());
	        
	        dockerCollectorTask.collect(dockerCollectorType);
	        
	  }
	  
	  
	  private Collector getDockerCollector(){
		    dockerCollectorType = new Collector();
		    dockerCollectorType.setName("Docker");
		    dockerCollectorType.setCollectorType(CollectorType.InfrastructureAsCode);
		    dockerCollectorType.setOnline(true);
		    dockerCollectorType.setEnabled(true);
			
			return dockerCollectorType;
	  }
	 
	   private ArrayList<CollectorItem> getDockerItems() {
	        ArrayList<CollectorItem> collectorItems = new ArrayList<CollectorItem>();

	        dockerItem1 = new CollectorItem();
	        dockerItem1.setEnabled(true);
	        dockerItem1.setId(new ObjectId("1c1ca42a258ad365fbb64ecc"));
	        dockerItem1.setCollectorId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        dockerItem1.setNiceName("docker-tfci1");
	        dockerItem1.getOptions().put("apiToken", "7Z4VJLmzWTB2ew.atlasv1.d3I90rBfOOV0ruG3zY1zW5ctbTqsFhNyTHPRBoSwzk3qxJ9wkcBwFPaLEqvW5qPChR0");
	        
	        collectorItems.add(dockerItem1);

	        return collectorItems;
	    }

	    private ArrayList<com.capitalone.dashboard.model.Component> components() {
	        ArrayList<com.capitalone.dashboard.model.Component> cArray = new ArrayList<com.capitalone.dashboard.model.Component>();
	        com.capitalone.dashboard.model.Component c = new Component();
	        c.setId(new ObjectId());
	        c.setName("COMPONENT1");
	        c.setOwner("PrC");

	        CollectorType dockeType = CollectorType.Docker;
	        CollectorItem ci1 = new CollectorItem();
	        ci1.setId(new ObjectId("1c1ca42a258ad365fbb64ecc"));
	        ci1.setNiceName("ci1");
	        ci1.setEnabled(true);
	        ci1.setPushed(false);
	        ci1.setCollectorId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        c.addCollectorItem(dockeType, ci1);

	        CollectorItem ci2 = new CollectorItem();
	        ci2.setId(new ObjectId("1c2ca42a258ad365fbb64ecc"));
	        ci2.setNiceName("ci2");
	        ci2.setEnabled(true);
	        ci2.setPushed(false);
	        ci2.setCollectorId(new ObjectId("111ca42a258ad365fbb64ecc"));
	        c.addCollectorItem(dockeType, ci2);

	        CollectorItem ci3 = new CollectorItem();
	        ci3.setId(new ObjectId("1c3ca42a258ad365fbb64ecc"));
	        ci3.setNiceName("ci3");
	        ci3.setEnabled(true);
	        ci3.setPushed(false);
	        ci3.setCollectorId(new ObjectId("222ca42a258ad365fbb64ecc"));
	        c.addCollectorItem(dockeType, ci3);

	        cArray.add(c);

	        return cArray;
	    
	  }
}
