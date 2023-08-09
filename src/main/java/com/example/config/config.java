package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.service.UserService;

@Configuration
@EnableWebSecurity
public class config {
	
	@Autowired
	UserService service;

//	@Bean
//	public UserDetailsService userdetail() {
//		UserDetails user = User.builder().username("shubham").password(encoder().encode("shubham")).roles("ADMIN", "USER").build();
//		UserDetails user1 = User.builder().username("kedar").password(encoder().encode("kedar")).roles("ADMIN", "USER").build();
//		
//		return new InMemoryUserDetailsManager(user, user1);
//	}
	
//	@Bean
//	public AuthenticationManager configGlobal(AuthenticationManagerBuilder build) throws Exception {
//		build.userDetailsService(service).passwordEncoder(encoder());
//		return build.build();
//	}
	
	@Bean
	public DaoAuthenticationProvider authenticate() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(service);
		auth.setPasswordEncoder(encoder());
		return auth;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
