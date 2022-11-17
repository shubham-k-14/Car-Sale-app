package com.car.sale.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;



//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="customer_details")
public class Customer 
extends User {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long userId;
	@NotNull(message = "Customer name cant be empty")
	@Size(min=4, message = "customer name contains minimum 2 char's")
	private String name;
	@Email
	@NotNull(message="enter email")
	private String email;
	@Size(min = 10 ,max=10, message = "customers contact No.should contain 10 digits")
	private String contactNo;
	private LocalDate dob;
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	@JsonManagedReference
	@Valid
	private List<Address> address ;
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Car> cars;
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Appointment> appointments;
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	public Customer() {
		// TODO Auto-generated constructor stub
	}
//	public Customer(long userId,
//			@NotNull(message = "Customer name cant be empty") @Size(min = 4, message = "customer name contains minimum 2 char's") String name,
//			@Email @NotNull(message = "enter email") String email,
//			@Size(min = 10, max = 10, message = "customers contact No.should contains  10 digits") String contactNo,
//			LocalDate dob, List<Address> address, List<Car> cars, List<Appointment> appointments, List<Order> orders) {
//		super();
//		//this.userId = userId;
//		this.name = name;
//		this.email = email;
//		this.contactNo = contactNo;
//		this.dob = dob;
//		this.address = address;
//		this.cars = cars;
//		this.appointments = appointments;
//		this.orders = orders;
//	}
//	public long getUserId() {
//		return userId;
//	}
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
//	@Override
//	public String toString() {
//		return "Customer [userId=" + userId + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo
//				+ ", dob=" + dob + ", address=" + address + ", cars=" + cars + ", appointments=" + appointments
//				+ ", orders=" + orders + "]";
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((appointments == null) ? 0 : appointments.hashCode());
		result = prime * result + ((cars == null) ? 0 : cars.hashCode());
		result = prime * result + ((contactNo == null) ? 0 : contactNo.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
//		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (appointments == null) {
			if (other.appointments != null)
				return false;
		} else if (!appointments.equals(other.appointments))
			return false;
		if (cars == null) {
			if (other.cars != null)
				return false;
		} else if (!cars.equals(other.cars))
			return false;
		if (contactNo == null) {
			if (other.contactNo != null)
				return false;
		} else if (!contactNo.equals(other.contactNo))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
//		if (userId != other.userId)
//			return false;
		return true;
	}
	
	
	
	
	
	
}
