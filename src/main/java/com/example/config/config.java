package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class config {

	@Bean
	public UserDetailsService userdetail() {
		UserDetails user = User.builder().username("shubham").password(encoder().encode("shubham")).roles("ADMIN", "USER").build();
		UserDetails user1 = User.builder().username("kedar").password(encoder().encode("kedar")).roles("ADMIN", "USER").build();
		return new InMemoryUserDetailsManager(user, user1);
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
