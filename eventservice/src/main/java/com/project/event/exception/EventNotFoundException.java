package com.project.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EventNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EventNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
