package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Security.JWTHelper;
import com.example.model.jwtRequest;
import com.example.model.jwtResponse;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userdetailservice;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTHelper helper;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/login")
	public ResponseEntity<jwtResponse> login(@RequestBody jwtRequest request){
		
		this.doAuthenticate(request.getUsername(), request.getPassword());
		
		UserDetails userdetails = userdetailservice.loadUserByUsername(request.getUsername());
		String token = this.helper.generateToken(userdetails);
		
		jwtResponse response = jwtResponse.builder().jwtToken(token)
				.username(userdetails.getUsername()).build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	private void doAuthenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authenticate);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("invalid username and password!!");
		}
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public String tokenExpired() {
		return "token Expired!!";
	}

}
