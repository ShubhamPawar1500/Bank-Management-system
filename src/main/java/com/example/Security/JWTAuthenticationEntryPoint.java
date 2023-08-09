package com.example.Security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter write = response.getWriter();
		write.println("Access Denied!!"+authException.getMessage());
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String BadCredexception() {
		return "Invalid Username and password";
	}

}
