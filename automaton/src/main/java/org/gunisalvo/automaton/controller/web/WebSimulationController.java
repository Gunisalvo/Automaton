package org.gunisalvo.automaton.controller.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.log4j.Logger;
import org.gunisalvo.automaton.controller.SimulationController;
import org.gunisalvo.automaton.controller.xml.AutomatonConfiguration;
import org.gunisalvo.automaton.monitor.SystemUnavailable;

@ApplicationScoped
public class WebSimulationController extends SimulationController{
	
	private static final Logger LOGGER = Logger.getLogger("[Automaton]");
	
	private Long initializationTime;
	
	@PostConstruct
	private void registerInitializationTime(){
		this.initializationTime = System.currentTimeMillis();
	}
	
	public AutomatonConfiguration getConfiguration() {
		return this.configuration;
	}

	public void registerRequestOnDowntime() {
		LOGGER.warn("request on downtime: " + System.currentTimeMillis());
		this.monitor.registerRequestOnDowntime();
	}

	public void registerRequest(boolean withFailure) {
		if(withFailure){
			LOGGER.warn("request failed: " + System.currentTimeMillis());
		}else{
			LOGGER.warn("request sucessfull: " + System.currentTimeMillis());
		}
		this.monitor.registerRequest(withFailure);
	}

	public boolean isAvailable() {
		return this.monitor.isAvailable();
	}

	public boolean isSlaComplient(Long timeUnit) {
		return this.monitor.isSlaComplient(timeUnit);
	}
	
	public boolean isAvailtabilityComplient(Long timeUnit) {
		return this.monitor.isAvailtabilityComplient(timeUnit);
	}
	
	public Long getInitializationTime() {
		return initializationTime;
	}
	
	public SystemUnavailable buildUnavailability(Long currentTimeUnit){
		LOGGER.warn("system Unavailability started: " + System.currentTimeMillis());
		return this.monitor.buildUnavailability(currentTimeUnit, ( currentTimeUnit-this.initializationTime ) );
	}

	public void warn(Object log) {
		LOGGER.warn(log);
	}

}
