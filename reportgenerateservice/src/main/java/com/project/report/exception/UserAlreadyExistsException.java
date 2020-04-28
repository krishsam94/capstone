package com.project.report.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT,message);
    }
}
