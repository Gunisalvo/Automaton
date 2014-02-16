package org.gunisalvo.automaton.monitor.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="serviceLevelAgreement")
public class ServiceLevelAgreement {
	
	private Double probabilityOfFailureOnDemand;
	
	private Double rateOfOccurrenceOfFailures;
	
	private Long meanTimeToFailure;
	
	private Double availability;

	public Double getProbabilityOfFailureOnDemand() {
		return probabilityOfFailureOnDemand;
	}

	public void setProbabilityOfFailureOnDemand(Double probabilityOfFailureOnDemand) {
		this.probabilityOfFailureOnDemand = probabilityOfFailureOnDemand;
	}

	public Double getRateOfOccurrenceOfFailures() {
		return rateOfOccurrenceOfFailures;
	}

	public void setRateOfOccurrenceOfFailures(Double rateOfOccurrenceOfFailures) {
		this.rateOfOccurrenceOfFailures = rateOfOccurrenceOfFailures;
	}

	public Long getMeanTimeToFailure() {
		return meanTimeToFailure;
	}

	public void setMeanTimeToFailure(Long meanTimeToFailure) {
		this.meanTimeToFailure = meanTimeToFailure;
	}

	public Double getAvailability() {
		return availability;
	}

	public void setAvailability(Double availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "ServiceLevelAgreement [probabilityOfFailureOnDemand="
				+ probabilityOfFailureOnDemand
				+ ", rateOfOccurrenceOfFailures=" + rateOfOccurrenceOfFailures
				+ ", meanTimeToFailure=" + meanTimeToFailure
				+ ", availability=" + availability + "]";
	}

}
