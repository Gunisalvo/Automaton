package org.gunisalvo.automaton.realTimeHook;

import java.util.Arrays;

import org.junit.Assert;

import org.gunisalvo.automaton.controller.realTime.TimerParameters;
import org.gunisalvo.automaton.controller.xml.AutomatonConfiguration;
import org.gunisalvo.automaton.controller.xml.Scripts;
import org.gunisalvo.automaton.monitor.SimulationMonitor;
import org.gunisalvo.automaton.realTimeHook.AutomatonTest;
import org.gunisalvo.automaton.realTimeHook.AutomatonTestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AutomatonTestFactoryTest {

	private AutomatonTestFactory guineaPig;
	
	@Before
	public void setup() {
		SimulationMonitor monitor = new SimulationMonitor();
		monitor.loadSla("/testSLA.xml");
		AutomatonConfiguration configuration = Mockito.mock(AutomatonConfiguration.class);
		Mockito.when(configuration.getRequestsMinute()).thenReturn(10);
		Mockito.when(configuration.getTotalRunningTime()).thenReturn(60000L);
		Mockito.when(configuration.getScripts()).thenReturn(new Scripts(Arrays.asList("org.gunisalvo.automaton.realTimeHook.Test1")));
		TimerParameters parameters = new TimerParameters(configuration);
		this.guineaPig = new AutomatonTestFactory(monitor, parameters);
	}
	
	@Test
	public void buildTest(){
		AutomatonTest test = this.guineaPig.loadTestByName("org.gunisalvo.automaton.realTimeHook.Test1");
		Assert.assertNotNull(test);
	}

}
