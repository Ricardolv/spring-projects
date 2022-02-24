package com.richard.brewer.service.exception;

public class ImpossibleDeleteEntityException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public ImpossibleDeleteEntityException(String message) {
		super(message);
	}

}
