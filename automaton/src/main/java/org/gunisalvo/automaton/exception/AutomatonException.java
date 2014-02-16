package org.gunisalvo.automaton.exception;

public class AutomatonException extends RuntimeException {

	private static final long serialVersionUID = -7222697443338090812L;
	
	public AutomatonException(Exception ex) {
		super(ex);
	}

	public AutomatonException(String message) {
		super(message);
	}

}
