package com.car.sale.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // this type of inheritance will create seperate table for each entity
@Table(name = "user_details")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@NotNull // regex checks if password has atleast 5 letters
	@Pattern(regexp = "^[a-zA-Z0-9@$]{5,}", message = "length must be atleast 5 letters and only '@' and '$' symbols are allowed")
	private String password;
	@NotNull(message = "role must be admin or customer") // regex checks for either admin or customer
	@Pattern(regexp = "([Cc]ustomer)|([aA]dmin)", message = "must be a 'customer' or 'admin'")
	private String role;

	public User() {

	}

	public User(long userId) {
		this.userId = userId;
	}

	public User(
			@Pattern(regexp = "^[a-zA-Z0-9@$]{5,}", message = "length must be atleast 5 letters and only '@' and '$' symbols are allowed") String password,
			@Pattern(regexp = "([Cc]ustomer)|([aA]dmin)", message = "must be a 'customer' or 'admin'") String role) {
		super();
		this.password = password;
		this.role = role;
	}

	public User(long userId, String password, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, role, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		User other = (User) obj;
		return other.password.equals(this.password) && other.role.equalsIgnoreCase(role) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}

}
