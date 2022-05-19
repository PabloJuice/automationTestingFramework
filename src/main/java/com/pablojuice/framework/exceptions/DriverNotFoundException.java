package com.pablojuice.framework.exceptions;


public class DriverNotFoundException extends RuntimeException {

	private static final String MESSAGE = "Can not find suitable driver for your browser. ";

	public DriverNotFoundException() {
		super(MESSAGE);
	}

	public DriverNotFoundException(String message) {
		super(MESSAGE + message);
	}

	public DriverNotFoundException(String message, Throwable cause) {
		super(MESSAGE + message, cause);
	}

	public DriverNotFoundException(Throwable cause) {
		super(cause);
	}

}
