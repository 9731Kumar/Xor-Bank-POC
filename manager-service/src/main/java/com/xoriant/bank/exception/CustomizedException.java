package com.xoriant.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomizedException 
{

	@ExceptionHandler
	public ResponseEntity<?> getHandleException(ResourceNotFoundException r)
	{
		return new ResponseEntity<String>(r.getMessage(),HttpStatus.NOT_FOUND);
	}
}
