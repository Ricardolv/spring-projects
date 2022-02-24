package com.richard.brewer.service.exception;

public class UserEmailExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserEmailExistsException(String message) {
		super(message);
	}

}
