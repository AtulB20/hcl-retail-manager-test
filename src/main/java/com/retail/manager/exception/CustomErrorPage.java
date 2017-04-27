package com.retail.manager.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorPage  implements ErrorController{

	private static final String PATH = "/error";
	public static final String ERROR_MESSAGE = "Requested url is not valid";
	public static final String DEFAULT_ERROR_VIEW = "error";
	
    @RequestMapping(value = PATH)
    public ResponseEntity<ErrorInfo> error(HttpServletRequest req, Exception e) {
    	ErrorInfo errorInfo = new ErrorInfo(DEFAULT_ERROR_VIEW, ERROR_MESSAGE);
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
