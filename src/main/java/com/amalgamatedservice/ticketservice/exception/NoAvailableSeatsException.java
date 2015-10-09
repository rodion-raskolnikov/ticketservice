package com.amalgamatedservice.ticketservice.exception;

public class NoAvailableSeatsException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoAvailableSeatsException() {
		super();
	}

	public NoAvailableSeatsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAvailableSeatsException(String message) {
		super(message);
	}

	public NoAvailableSeatsException(Throwable cause) {
		super(cause);
	}

}
