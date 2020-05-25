package com.stackroute.favouriteservice.bean;

public class AuthToken {

	public AuthToken(boolean isAuthenticated, String token) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.token = token;
	}
	
	public AuthToken() {
	}

	private boolean isAuthenticated;

	private String token;

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
