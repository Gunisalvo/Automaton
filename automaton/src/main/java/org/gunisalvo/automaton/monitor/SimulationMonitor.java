package org.gunisalvo.automaton.monitor;

import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.gunisalvo.automaton.exception.AutomatonException;
import org.gunisalvo.automaton.monitor.xml.ServiceLevelAgreement;

public class SimulationMonitor {
	
	private static Long MAX_DOWNTIME = (long)(5 * 1000);
	
	private ServiceLevelAgreement sla;
	
	private AtomicInteger numberOfFailures;
	
	private AtomicInteger numberOfRequests;
	
	private AtomicInteger numberOfRequestsDuringDowntime;
	
	private AtomicLong downtime;
	
	private AtomicLong currentDowntimeStart;
	
	@PostConstruct
	public void reset(){
		this.numberOfFailures = new AtomicInteger(1);
		this.numberOfRequests = new AtomicInteger(0);
		this.numberOfRequestsDuringDowntime = new AtomicInteger(0);
		this.downtime = new AtomicLong(0);
		this.currentDowntimeStart = null;
		this.sla = null;
	}

	public void loadSla(String slaConfigurationPath) {
		try{
			InputStream is = this.getClass().getResourceAsStream(slaConfigurationPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ServiceLevelAgreement.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			this.sla = (ServiceLevelAgreement) jaxbUnmarshaller.unmarshal(is);
		}catch(Exception ex){
			throw new AutomatonException(ex);
		}
	}
	
	public void loadSla(ServiceLevelAgreement serviceLevelAgreement) {
		this.sla = serviceLevelAgreement;
	}
	
	public boolean isSlaComplient(Long timeUnits){
		if(this.sla == null){
			throw new AutomatonException("no Service Level Agreement defined");
		}
		return isTimeComplient(timeUnits) && isRateComplient(timeUnits) && isProbabilityComplient() && isTimeComplient(timeUnits);
	}
	
	public boolean isTimeComplient(Long timeUnits){
		if(this.sla == null){
			throw new AutomatonException("no Service Level Agreement defined");
		}
		Long serviceLevel = this.sla.getMeanTimeToFailure();
		return ( timeUnits / serviceLevel ) >= this.numberOfFailures.get()-1;
	}
	
	public boolean isRateComplient(Long timeUnits){
		if(this.sla == null){
			throw new AutomatonException("no Service Level Agreement defined");
		}
		Double rate = ( timeUnits == 0 ) ? 0.0 : (double)(this.numberOfFailures.get()-1) / (double)timeUnits;
		return rate <= this.sla.getRateOfOccurrenceOfFailures();
	}
	
	public boolean isProbabilityComplient(){
		if(this.sla == null){
			throw new AutomatonException("no Service Level Agreement defined");
		}
		Double rate = ( this.numberOfRequests.get() == 0 ) ? 0.0 : (double)(this.numberOfFailures.get() - 1) / (double)this.numberOfRequests.get();
		return rate <= this.sla.getProbabilityOfFailureOnDemand();
	}
	
	public boolean isAvailtabilityComplient(Long timeUnits){
		if(this.sla == null){
			throw new AutomatonException("no Service Level Agreement defined");
		}
		Long totalDowntime;
		if(this.currentDowntimeStart != null){
			totalDowntime = this.downtime.get() + timeUnits - this.currentDowntimeStart.get();
		}else{
			totalDowntime = this.downtime.get();
		}
		Double downTimeRate = ( timeUnits == 0 ) ? 0.0 : (double)totalDowntime / (double)timeUnits;
		return (1.0 - downTimeRate) >= this.sla.getAvailability();
	}
	
	public void registerRequest(boolean withFailure){
		this.numberOfRequests.getAndIncrement();
		if(withFailure || !isAvailable()){
			this.numberOfFailures.getAndIncrement();
		}
	}

	public ServiceLevelAgreement getSla() {
		return sla;
	}

	public Integer getNumberOfFailures() {
		return this.numberOfFailures.get() - 1;
	}
	
	public Integer getNumberOfRequests() {
		return this.numberOfRequests.get();
	}
	
	public Long getTotalDowntime(){
		return this.downtime.get();
	}
	
	public void startDowntime(Long timeUnit){
		if(this.currentDowntimeStart != null){
			throw new AutomatonException("simulation is currently on downtime-state");
		}
		this.currentDowntimeStart = new AtomicLong(timeUnit);
	}
	
	public void endDowntime(Long timeUnit){
		if(this.currentDowntimeStart == null){
			throw new AutomatonException("simulation is not on downtime-state");
		}
		synchronized (this) {
			Long currentDowntimeInterval  = timeUnit - this.currentDowntimeStart.get();
			this.downtime.getAndAdd(currentDowntimeInterval);
		}
		this.currentDowntimeStart = null;
	}
	
	public boolean isAvailable(){
		return this.currentDowntimeStart == null;
	}

	public SystemUnavailable buildUnavailability(Long currentTimeUnit, Long simulationRunningTime) {
		Long remainingDowntime = (long)((simulationRunningTime * (1.0 - this.sla.getAvailability()))-this.downtime.get());
		Long unavailableTime = (Math.abs(new Random().nextLong())) % remainingDowntime;
		
		if(unavailableTime > 0L){
			return new SystemUnavailable(this, currentTimeUnit, unavailableTime);
		}else{
			return null;
		}
		
	}
	
	public SystemUnavailable buildUnavailability(Long currentTimeUnit) {
		
		Long unavailableTime = (Math.abs(new Random().nextLong())) % MAX_DOWNTIME;
		
		if(unavailableTime > 0L){
			return new SystemUnavailable(this, currentTimeUnit, unavailableTime);
		}else{
			return null;
		}
		
	}

	public void registerRequestOnDowntime() {
		this.numberOfRequestsDuringDowntime.getAndIncrement();
	}

	public int getNumberOfRequestsDuringDowntime() {
		return this.numberOfRequestsDuringDowntime.get();
	}

}
