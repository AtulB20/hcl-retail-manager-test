//package com.retail.manager.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.ValidationException;
//
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.retail.manager.exception.ErrorInfo;
//
//@RestController
//public class CustomErrorPage implements ErrorController{
//
//	private static final String PATH = "/error";
//	public static final String ERROR_MESSAGE = "Requested url is not valid";
//	
//    @RequestMapping(value = PATH)
//    public ResponseEntity<ErrorInfo> error(HttpServletRequest req, Exception e) throws ValidationException{
//    	throw new ValidationException(ERROR_MESSAGE);
//    }
//
//    @Override
//    public String getErrorPath() {
//        return PATH;
//    }
//}
