package com.retail.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidCoordinatesException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_MESSAGE = "Invalid location parameters, location must be a valid number";
	
	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
