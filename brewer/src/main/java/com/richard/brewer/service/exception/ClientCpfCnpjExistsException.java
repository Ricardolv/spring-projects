package com.richard.brewer.service.exception;

public class ClientCpfCnpjExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ClientCpfCnpjExistsException(String message) {
		super(message);
	}

}
