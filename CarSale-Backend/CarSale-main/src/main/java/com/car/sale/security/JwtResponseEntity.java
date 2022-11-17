package com.car.sale.security;

public class JwtResponseEntity {
	private int userId;
	private String role;
	private String password;
	private String token;
	public JwtResponseEntity() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public JwtResponseEntity(int userId, String role, String password, String token) {
		super();
		this.userId = userId;
		this.role = role;
		this.password = password;
		this.token = token;
	}
}
