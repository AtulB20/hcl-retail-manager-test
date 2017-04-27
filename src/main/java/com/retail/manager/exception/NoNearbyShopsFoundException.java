package com.retail.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NoNearbyShopsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_MESSAGE = "No nearby shops found for given location";
	
	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
