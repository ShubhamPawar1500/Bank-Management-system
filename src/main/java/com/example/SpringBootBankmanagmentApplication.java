package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBankmanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBankmanagmentApplication.class, args);
	}
	
	
//	@Bean
//	public WebMvcConfigurer corconfig() {
//		return new WebMvcConfigurer() {
//			
//			public void addcorsmapping(CorsRegistry reg) {
//				reg.addMapping("/**").allowedOrigins("http://localhost:3000/").allowCredentials(true);
//			}
//		};
//	}
	
	

}
