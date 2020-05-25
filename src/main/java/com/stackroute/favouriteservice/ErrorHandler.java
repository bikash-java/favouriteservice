package com.stackroute.favouriteservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.bean.ErrorMessage;
import com.stackroute.favouriteservice.exception.AuthorizationException;
import com.stackroute.favouriteservice.exception.NoDataFoundException;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "http://localhost:4200")
public class ErrorHandler {
	
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleNoDataFoundError() {
		return new ErrorMessage("404", "No data found");
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthorizationException.class)
	public ErrorMessage handleAuthorizationError() {
		return new ErrorMessage("401", "You are not authorized");
	}

}
