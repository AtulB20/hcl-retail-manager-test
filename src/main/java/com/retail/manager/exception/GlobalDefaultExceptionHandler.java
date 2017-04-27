package com.retail.manager.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.maps.errors.ApiException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = {InvalidShopDetailsException.class, InvalidCoordinatesException.class, ValidationException.class})
	public ResponseEntity<ErrorInfo> badRequestExceptionHandler(Exception e){
		ErrorInfo errorInfo = new ErrorInfo(DEFAULT_ERROR_VIEW, e.getMessage());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {ApiException.class, NoNearbyShopsFoundException.class})
	public ResponseEntity<ErrorInfo> noDetailsForInputArguments(Exception e){
		ErrorInfo errorInfo = new ErrorInfo(DEFAULT_ERROR_VIEW, e.getMessage());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NO_CONTENT);
	}	
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorInfo> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ErrorInfo errorInfo = new ErrorInfo(DEFAULT_ERROR_VIEW, e.getMessage());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}

} 