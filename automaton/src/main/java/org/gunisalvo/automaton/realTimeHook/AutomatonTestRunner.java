package org.gunisalvo.automaton.realTimeHook;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.gunisalvo.automaton.exception.AutomatonException;

public class AutomatonTestRunner implements Runnable{

	private final List<AutomatonTest> tests;
	
	private AtomicInteger runCounter;
	
	public AutomatonTestRunner(List<AutomatonTest> tests) {
		if(tests == null || tests.size() == 0){
			throw new AutomatonException("empty test set");
		}
		this.runCounter = new AtomicInteger(0);
		this.tests = tests;
	}
	
	@Override
	public void run() {
		new Thread(this.tests.get(this.runCounter.get() % this.tests.size())).start();
		this.runCounter.getAndAdd(1);
	}

}
