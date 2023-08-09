package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/login").permitAll().requestMatchers("/auth/register").permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer customize() {
		return (web) -> web.ignoring().requestMatchers("/auth/register").and().ignoring().requestMatchers("/auth/login");
	}
	
	
	@Bean
	public FilterRegistrationBean coresFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsconfig = new CorsConfiguration();
		corsconfig.setAllowCredentials(true);
//		corsconfig.addExposedHeader("Access-Control-Allow-Origin");
//		corsconfig.addExposedHeader("Access-Control-Allow-Credentials");
		corsconfig.addAllowedOriginPattern("http://localhost:3000/");
		corsconfig.setAllowedMethods(Arrays.asList("POST","OPTIONS", "GET", "DELETE", "PUT"));
		corsconfig.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//		corsconfig.addAllowedHeader("Authorization");
//		corsconfig.addAllowedHeader("Content-Type");
//		corsconfig.addAllowedHeader("Accept");
//		corsconfig.addAllowedHeader("POST");
//		corsconfig.addAllowedHeader("GET");
//		corsconfig.addAllowedHeader("DELETE");
//		corsconfig.addAllowedHeader("PUT");
//		corsconfig.addAllowedHeader("OPTIONS");
//		corsconfig.addAllowedHeader("HEAD");
//		corsconfig.setMaxAge(3600L);
		
		source.registerCorsConfiguration("/**", corsconfig);
		
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return bean;
		
	}
	

}
