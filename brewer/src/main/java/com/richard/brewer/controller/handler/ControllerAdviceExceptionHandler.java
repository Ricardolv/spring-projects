package com.richard.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.richard.brewer.service.exception.NameExistsException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	
	@ExceptionHandler(NameExistsException.class)
	public ResponseEntity<String> handleNameExistsException(NameExistsException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
