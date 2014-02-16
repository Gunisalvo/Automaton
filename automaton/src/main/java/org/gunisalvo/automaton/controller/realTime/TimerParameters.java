package org.gunisalvo.automaton.controller.realTime;

import org.gunisalvo.automaton.controller.xml.AutomatonConfiguration;

public class TimerParameters {

	private static final Long MINUTE = (long)(60 * 1000);

	private final Long startTime;

	private final Long endTime;

	private final Long delay;

	public TimerParameters(AutomatonConfiguration configuration) {
		Long totalRunningTime = configuration.getTotalRunningTime();
		double rateOfMinutes = (double) totalRunningTime / MINUTE;
		this.startTime = System.currentTimeMillis();
		this.endTime = this.startTime + totalRunningTime;
		this.delay = (long) ( totalRunningTime / ( configuration.getRequestsMinute() * ( rateOfMinutes ) ) );
	}

	public Long getStartTime() {
		return startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public Long getDelay() {
		return delay;
	}

}
