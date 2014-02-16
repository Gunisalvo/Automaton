package org.gunisalvo.automaton.controller;

import org.gunisalvo.automaton.controller.realTime.RealTimeSimulationController;
import org.gunisalvo.automaton.controller.xml.AutomatonConfiguration;
import org.gunisalvo.automaton.controller.xml.SimulationResults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActiveSimulationControllerTest {

	private RealTimeSimulationController guineaPig = new RealTimeSimulationController();

	@Before
	public void resetMonitor() {
		this.guineaPig.reset();
	}

	@Test
	public void buildControllerTest() {
		this.guineaPig.configure("/testConfiguration.xml");
		
		AutomatonConfiguration configuration = this.guineaPig.getConfiguration();
		
		Assert.assertEquals(100, (int)configuration.getRequestsMinute());
		Assert.assertEquals(120000L, (long)configuration.getTotalRunningTime());
		Assert.assertEquals(3, (int)configuration.getScripts().size());
	}
	
	@Test
	public void simulationTest() throws InterruptedException {
		this.guineaPig.configure("/testConfiguration.xml");
		SimulationResults results = this.guineaPig.startSimulation();
		System.out.println(results);
	}

}
