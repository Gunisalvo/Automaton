package org.gunisalvo.automaton.controller.realTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.gunisalvo.automaton.controller.SimulationController;
import org.gunisalvo.automaton.controller.xml.AutomatonConfiguration;
import org.gunisalvo.automaton.controller.xml.Scripts;
import org.gunisalvo.automaton.controller.xml.SimulationResults;
import org.gunisalvo.automaton.monitor.SimulationMonitor;
import org.gunisalvo.automaton.monitor.SystemUnavailable;
import org.gunisalvo.automaton.realTimeHook.AutomatonTest;
import org.gunisalvo.automaton.realTimeHook.AutomatonTestFactory;
import org.gunisalvo.automaton.realTimeHook.AutomatonTestRunner;

public class RealTimeSimulationController extends SimulationController{
	
	private ScheduledExecutorService timer = Executors.newScheduledThreadPool(10000);
	
	private TimerParameters timeParameters;

	public void reset(){
		if(this.monitor == null){
			this.monitor = new SimulationMonitor();
		}
		this.monitor.reset();
	}

	private List<AutomatonTest> loadScripts(TimerParameters parameters) {
		AutomatonTestFactory factory = new AutomatonTestFactory(this.monitor,parameters);
		List<AutomatonTest> tests = new ArrayList<AutomatonTest>();
		Scripts scripts = this.configuration.getScripts();
		for(String scriptName : scripts.getScript()){
			tests.add(factory.loadTestByName(scriptName));
		}
		return tests;
	}

	public SimulationResults startSimulation() throws InterruptedException {
		this.timeParameters = new TimerParameters(this.configuration);
		AutomatonTestRunner runner = new AutomatonTestRunner(loadScripts(timeParameters));
		this.timer.scheduleWithFixedDelay(runner, 0 , this.timeParameters.getDelay(), TimeUnit.MILLISECONDS);
		
		while(System.currentTimeMillis() < this.timeParameters.getEndTime()){
			Long simulationTime = System.currentTimeMillis() - this.timeParameters.getStartTime();
			if(this.monitor.isSlaComplient(simulationTime) && this.monitor.isAvailable()){
				Long timeUnit = System.currentTimeMillis() - this.timeParameters.getStartTime();
				SystemUnavailable unavailability = this.monitor.buildUnavailability(timeUnit, this.configuration.getTotalRunningTime());
				if(unavailability != null){
					new Thread(unavailability).start();
				}
			}
			Thread.sleep(1L);
		}
		
		this.timeParameters = null;
		return new SimulationResults(this.monitor,this.configuration);
	}

	public AutomatonConfiguration getConfiguration() {
		return this.configuration;
	}

}
