package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Security.JWTAuthenticationEntryPoint;
import com.example.Security.JWTAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JWTAuthenticationEntryPoint point;
	
	@Autowired
	private JWTAuthenticationFilter filter;
	
	@Bean 
	public SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/")
						.authenticated().requestMatchers("/auth/login").permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
