package org.gunisalvo.automaton.webHook.exception;

public class RequestFaliedException extends RuntimeException {

	private static final long serialVersionUID = 762908252359097991L;
	
	private final Long timestamp;
	
	public RequestFaliedException(Long timestamp){
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
