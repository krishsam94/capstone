package com.project.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EventAlreadyExistsException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EventAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT,message);
    }
}
