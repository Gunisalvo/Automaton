package org.gunisalvo.automaton.realTimeHook;

import java.lang.reflect.Constructor;

import org.gunisalvo.automaton.controller.realTime.TimerParameters;
import org.gunisalvo.automaton.exception.AutomatonException;
import org.gunisalvo.automaton.monitor.SimulationMonitor;

public class AutomatonTestFactory {
	
	private SimulationMonitor monitor;

	private TimerParameters parameters;
	
	public AutomatonTestFactory() {
	}
	
	public AutomatonTestFactory(SimulationMonitor monitor, TimerParameters parameters){
		this.monitor = monitor;
		this.parameters = parameters;
	}
	
	@SuppressWarnings("unchecked")
	public AutomatonTest loadTestByName(String testName){
		try {
			Class<AutomatonTest> testClass = ( Class<AutomatonTest> ) Class.forName(testName);
			Constructor<AutomatonTest> c = ( Constructor<AutomatonTest> ) testClass.getConstructors()[0];
			AutomatonTest seleniumTest = c.newInstance();
			seleniumTest.configure( this.parameters, this.monitor );
			return seleniumTest;
		} catch ( Exception e ) {
			throw new AutomatonException(e);
		}
	}
}
