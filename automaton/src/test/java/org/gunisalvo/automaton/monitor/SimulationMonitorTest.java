package org.gunisalvo.automaton.monitor;

import org.junit.Assert;

import org.gunisalvo.automaton.exception.AutomatonException;
import org.gunisalvo.automaton.monitor.xml.ServiceLevelAgreement;
import org.junit.Before;
import org.junit.Test;

public class SimulationMonitorTest {

	private SimulationMonitor guineaPig = new SimulationMonitor();
	
	@Before
	public void resetMonitor(){
		this.guineaPig.reset();
	}
	
	@Test(expected=AutomatonException.class)
	public void badFilenameTest() {
		this.guineaPig.loadSla("badFilename");
	}
	
	@Test
	public void buildMonitorTest() {
		Assert.assertEquals((int)0, (int)this.guineaPig.getNumberOfFailures());
		
		this.guineaPig.loadSla("/testSLA.xml");
		
		ServiceLevelAgreement sla = this.guineaPig.getSla();
		
		Assert.assertEquals(0.2, (double)sla.getProbabilityOfFailureOnDemand(),Double.MIN_VALUE);
		Assert.assertEquals(0.3, (double)sla.getRateOfOccurrenceOfFailures(),Double.MIN_VALUE);
		Assert.assertEquals((long)500,(long)sla.getMeanTimeToFailure());
		Assert.assertEquals(0.8, (double)sla.getAvailability(),Double.MIN_VALUE);
	}
	
	@Test
	public void countersTest() {
		this.guineaPig.loadSla("/testSLA.xml");
		Assert.assertEquals(0, (int)this.guineaPig.getNumberOfFailures());
		Assert.assertEquals(0, (int)this.guineaPig.getNumberOfRequests());
		
		this.guineaPig.registerRequest(true);
		Assert.assertEquals(1, (int)this.guineaPig.getNumberOfFailures());
		Assert.assertEquals(1, (int)this.guineaPig.getNumberOfRequests());
		
		this.guineaPig.registerRequest(true);
		this.guineaPig.registerRequest(false);
		Assert.assertEquals(2, (int)this.guineaPig.getNumberOfFailures());
		Assert.assertEquals(3, (int)this.guineaPig.getNumberOfRequests());
	}
	
	@Test
	public void downtimeTest() {
		this.guineaPig.startDowntime(33L);
		this.guineaPig.endDowntime(1033L);
		Assert.assertEquals(1000L,(long)this.guineaPig.getTotalDowntime());
		this.guineaPig.startDowntime(1207L);
		this.guineaPig.endDowntime(1307L);
		Assert.assertEquals(1100L,(long)this.guineaPig.getTotalDowntime());
	}
	
	@Test(expected=AutomatonException.class)
	public void downtimeBadStartTest(){
		this.guineaPig.startDowntime(33L);
		this.guineaPig.startDowntime(773L);
	}
	
	@Test(expected=AutomatonException.class)
	public void downtimeBadEndTest(){
		this.guineaPig.startDowntime(33L);
		this.guineaPig.endDowntime(1307L);
		this.guineaPig.endDowntime(1317L);
	}
	
	@Test
	public void timeCompliancyTest() {
		this.guineaPig.loadSla("/testSLA.xml");
		Assert.assertTrue(this.guineaPig.isTimeComplient(0L));
		this.guineaPig.registerRequest(true);
		Assert.assertFalse(this.guineaPig.isTimeComplient(0L));
		Assert.assertFalse(this.guineaPig.isTimeComplient(499L));
		Assert.assertTrue(this.guineaPig.isTimeComplient(500L));
		this.guineaPig.registerRequest(true);
		Assert.assertFalse(this.guineaPig.isTimeComplient(500L));
	}
	
	@Test
	public void probabilityCompliancyTest() {
		this.guineaPig.loadSla("/testSLA.xml");
		Assert.assertTrue(this.guineaPig.isProbabilityComplient());
		this.guineaPig.registerRequest(false);
		Assert.assertTrue(this.guineaPig.isProbabilityComplient());
		this.guineaPig.registerRequest(false);
		this.guineaPig.registerRequest(false);
		this.guineaPig.registerRequest(true);
		Assert.assertFalse(this.guineaPig.isProbabilityComplient());
		this.guineaPig.registerRequest(false);
		Assert.assertTrue(this.guineaPig.isProbabilityComplient());
	}
	
	@Test
	public void rateCompliancyTest() {
		this.guineaPig.loadSla("/testSLA.xml");
		Assert.assertTrue(this.guineaPig.isRateComplient(0L));
		this.guineaPig.registerRequest(true);
		Assert.assertFalse(this.guineaPig.isRateComplient(1L));
		Assert.assertFalse(this.guineaPig.isRateComplient(3L));
		Assert.assertTrue(this.guineaPig.isRateComplient(4L));
		this.guineaPig.registerRequest(true);
		this.guineaPig.registerRequest(true);
		Assert.assertFalse(this.guineaPig.isRateComplient(9L));
		Assert.assertTrue(this.guineaPig.isRateComplient(10L));
	}
	
	@Test
	public void availabilityCompliancyTest() {
		this.guineaPig.loadSla("/testSLA.xml");
		Assert.assertTrue(this.guineaPig.isAvailtabilityComplient(0L));
		this.guineaPig.startDowntime(10L);
		Assert.assertTrue(this.guineaPig.isAvailtabilityComplient(12L));
		Assert.assertFalse(this.guineaPig.isAvailtabilityComplient(13L));
		this.guineaPig.endDowntime(30L);
		Assert.assertFalse(this.guineaPig.isAvailtabilityComplient(99L));
		Assert.assertTrue(this.guineaPig.isAvailtabilityComplient(100L));
	}

}
