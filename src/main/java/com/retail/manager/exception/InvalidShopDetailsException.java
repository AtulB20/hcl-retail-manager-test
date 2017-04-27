package com.retail.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class InvalidShopDetailsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_MESSAGE = "Invalid shop details, please enter correct shop details";
	
	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
