package org.gunisalvo.automaton.realTimeHook;

import org.gunisalvo.automaton.controller.realTime.TimerParameters;
import org.gunisalvo.automaton.monitor.SimulationMonitor;

public abstract class AutomatonTest implements Runnable{
	
	private SimulationMonitor monitor;

	private TimerParameters parameters;

	protected String baseUrl;
	
	public void configure(TimerParameters parameters, SimulationMonitor monitor) {
		this.parameters = parameters;
		this.monitor = monitor;
	}

	public void run() {
		if (this.monitor.isSlaComplient(System.currentTimeMillis() - parameters.getStartTime())) {
			if (this.monitor.isAvailable()) {
				this.monitor.registerRequest(true);
			} else {
				this.monitor.registerRequestOnDowntime();
			}
		} else {
			if (this.monitor.isAvailable()) {
				this.monitor.registerRequest(false);
				runTest();
			} else {
				this.monitor.registerRequestOnDowntime();
			}
		}
	}

	protected abstract void runTest();

}
