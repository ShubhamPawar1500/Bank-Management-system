package com.example.model;

public class jwtResponse {
	
	private String jwtToken;
	
	private String username;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public jwtResponse(String jwtToken, String username) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
	}

	public jwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static jwtResponseBuilder builder() {
		return new jwtResponseBuilder();
	}

	public static class jwtResponseBuilder{
		private String jwtToken;
		private String username;
		
		public jwtResponseBuilder() {
			
		}
		
		public jwtResponseBuilder jwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
			return this;
		}
		
		public jwtResponseBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public jwtResponse build() {
			return new jwtResponse(jwtToken, username);
		}
			
		
	}
	
	
}
