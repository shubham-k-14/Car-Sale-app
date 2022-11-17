package com.car.sale.security;

public class JwtUser {
	private int userId;
	private String role;
	private String password;
	public JwtUser() {
		// TODO Auto-generated constructor stub
	}
	public JwtUser(int userId, String role, String password) {
		super();
		this.userId = userId;
		this.role = role;
		this.password = password;
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
	
	
	
}
