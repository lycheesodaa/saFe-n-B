package com.cs203.project.util;

import java.util.Base64;

public class AuthenticationRequest {
	
	private String email;
	private String password;
	
	
	public AuthenticationRequest() {
	}

	public AuthenticationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashedPassword() {
	String hashedPassword = Base64.getEncoder().encodeToString(this.getPassword().getBytes());
//	String hashedPassword = new BCryptPasswordEncoder().encode(this.getPassword());
    return hashedPassword;
}
	
	
	
}
