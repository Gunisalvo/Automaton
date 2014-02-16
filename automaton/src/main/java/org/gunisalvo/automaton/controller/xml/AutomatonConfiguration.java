package org.gunisalvo.automaton.controller.xml;

import javax.xml.bind.annotation.XmlRootElement;

import org.gunisalvo.automaton.monitor.xml.ServiceLevelAgreement;

@XmlRootElement(name="automaton")
public class AutomatonConfiguration {
	
	private String serviceLevelAgreementFile;
	
	private ServiceLevelAgreement serviceLevelAgreement;
	
	private Long totalRunningTime;
	
	private Integer requestsMinute;
	
	private Scripts scripts;

	public String getServiceLevelAgreementFile() {
		return serviceLevelAgreementFile;
	}

	public void setServiceLevelAgreementFile(String serviceLevelAgreementFile) {
		this.serviceLevelAgreementFile = serviceLevelAgreementFile;
	}

	public Long getTotalRunningTime() {
		return totalRunningTime;
	}

	public void setTotalRunningTime(Long totalRunningTime) {
		this.totalRunningTime = totalRunningTime;
	}

	public Integer getRequestsMinute() {
		return requestsMinute;
	}

	public void setRequestsMinute(Integer requestsMinute) {
		this.requestsMinute = requestsMinute;
	}

	public Scripts getScripts() {
		return scripts;
	}

	public void setScripts(Scripts scripts) {
		this.scripts = scripts;
	}
	
	public ServiceLevelAgreement getServiceLevelAgreement() {
		return serviceLevelAgreement;
	}

	public void setServiceLevelAgreement(ServiceLevelAgreement serviceLevelAgreement) {
		this.serviceLevelAgreement = serviceLevelAgreement;
	}

	@Override
	public String toString() {
		return "AutomatonConfiguration [serviceLevelAgreementFile="
				+ serviceLevelAgreementFile + ", serviceLevelAgreement="
				+ serviceLevelAgreement + ", totalRunningTime="
				+ totalRunningTime + ", requestsMinute=" + requestsMinute
				+ ", scripts=" + scripts + "]";
	}

}
