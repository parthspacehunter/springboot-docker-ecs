package com.cow.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	  @ExceptionHandler(Exception.class)
	    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception,WebRequest request) {
	        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
	                request.getDescription(false));

	        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	    @ExceptionHandler(CowNotFoundException.class)
	    public ResponseEntity<ExceptionResponse> handleCowNotFoundException(CowNotFoundException cowNotFounException,WebRequest request) {
	        ExceptionResponse exceptionResponse = new ExceptionResponse(
	        		cowNotFounException.getMessage(),
	                request.getDescription(false));
	        


	        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	    }
	   
	    @ExceptionHandler(NoSuchElementException.class)
	    public ResponseEntity<Object> NoSuchElementException(CowNotFoundException cowNotFounException,
                                 WebRequest request) {
	    	ExceptionResponse exceptionResponse = new ExceptionResponse(cowNotFounException.getMessage(),
	    			request.getDescription(false));


	    	return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	    }
	    
}
