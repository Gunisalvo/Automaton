package org.gunisalvo.automaton.webHook.exception;

public class ServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 7886145274008306195L;
	
	private final Long timestamp;

	public ServiceUnavailableException(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

}
