package com.richard.tamingthymeleaf.application.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(Exception e) {
        super(e);
    }
}
