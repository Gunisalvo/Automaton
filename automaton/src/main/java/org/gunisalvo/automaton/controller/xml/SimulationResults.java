package org.gunisalvo.automaton.controller.xml;

import javax.xml.bind.annotation.XmlRootElement;

import org.gunisalvo.automaton.monitor.SimulationMonitor;
import org.gunisalvo.automaton.monitor.xml.ServiceLevelAgreement;

@XmlRootElement(name="automatonResults")
public class SimulationResults {

	private ServiceLevelAgreement serviceLevelAgreement;
	
	private AutomatonConfiguration configuration;
	
	private Integer numberOfRequests;
	
	private Integer numberOfFailures;
	
	private Integer numberOfRequestsDuringDowntime;
	
	private Long totalDowntime;
	
	public SimulationResults() {
	}
	
	public SimulationResults(SimulationMonitor monitor, AutomatonConfiguration configuration) {
		this.serviceLevelAgreement = monitor.getSla();
		this.configuration = configuration;
		this.numberOfRequests = monitor.getNumberOfRequests();
		this.numberOfFailures = monitor.getNumberOfFailures();
		this.numberOfRequestsDuringDowntime = monitor.getNumberOfRequestsDuringDowntime();
		this.totalDowntime = monitor.getTotalDowntime();
	}

	public ServiceLevelAgreement getServiceLevelAgreement() {
		return serviceLevelAgreement;
	}

	public void setServiceLevelAgreement(ServiceLevelAgreement serviceLevelAgreement) {
		this.serviceLevelAgreement = serviceLevelAgreement;
	}

	public Integer getNumberOfRequests() {
		return numberOfRequests;
	}

	public void setNumberOfRequests(Integer numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}

	public Integer getNumberOfFailures() {
		return numberOfFailures;
	}

	public void setNumberOfFailures(Integer numberOfFailures) {
		this.numberOfFailures = numberOfFailures;
	}

	public Integer getNumberOfRequestsDuringDowntime() {
		return numberOfRequestsDuringDowntime;
	}

	public void setNumberOfRequestsDuringDowntime(
			Integer numberOfRequestsDuringDowntime) {
		this.numberOfRequestsDuringDowntime = numberOfRequestsDuringDowntime;
	}

	public Long getTotalDowntime() {
		return totalDowntime;
	}

	public void setTotalDowntime(Long totalDowntime) {
		this.totalDowntime = totalDowntime;
	}

	public AutomatonConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(AutomatonConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public String toString() {
		return "SimulationResults [serviceLevelAgreement="
				+ serviceLevelAgreement + ", configuration=" + configuration
				+ ", numberOfRequests=" + numberOfRequests
				+ ", numberOfFailures=" + numberOfFailures
				+ ", numberOfRequestsDuringDowntime="
				+ numberOfRequestsDuringDowntime + ", totalDowntime="
				+ totalDowntime + "]";
	}
	
}
