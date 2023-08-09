package com.example.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

import com.example.Exception.IdGreaterThan40Exception;

@RestControllerAdvice
public class ErrorController {
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IdGreaterThan40Exception.class)
	public Map<String, String> IdGreaterThan40Exception(IdGreaterThan40Exception ex){
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		return error;
	}
	

}
