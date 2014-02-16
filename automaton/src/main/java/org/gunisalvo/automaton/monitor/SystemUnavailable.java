package org.gunisalvo.automaton.monitor;


public class SystemUnavailable implements Runnable {

	private final SimulationMonitor monitor;
	
	private final Long startTime;
	
	private final Long unavailableTime;
	
	public SystemUnavailable(SimulationMonitor monitor, Long startTime, Long unavailableTime) {
		this.monitor = monitor;
		this.unavailableTime = unavailableTime;
		this.startTime = startTime;
	}

	public void run() {
		
		this.monitor.startDowntime(startTime);
		
		try {
			Thread.sleep(this.unavailableTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.monitor.endDowntime( startTime + unavailableTime);

	}

}
