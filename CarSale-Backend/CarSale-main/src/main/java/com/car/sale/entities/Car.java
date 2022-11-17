package com.car.sale.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "Car_details")
public class Car {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long carId;
	private String brand;
	private String model;
	private String variant;
	private LocalDate registrationYear;
	private String registrationState;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_Id")
	@JsonBackReference
	private Customer customer;
	public Car() {
		// TODO Auto-generated constructor stub
	}
	public Car(long carId, String brand, String model, String variant, LocalDate registrationYear,
			String registrationState, Customer customer) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.variant = variant;
		this.registrationYear = registrationYear;
		this.registrationState = registrationState;
		this.customer = customer;
	}
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public LocalDate getRegistrationYear() {
		return registrationYear;
	}
	public void setRegistrationYear(LocalDate registrationYear) {
		this.registrationYear = registrationYear;
	}
	public String getRegistrationState() {
		return registrationState;
	}
	public void setRegistrationState(String registrationState) {
		this.registrationState = registrationState;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", brand=" + brand + ", model=" + model + ", variant=" + variant
				+ ", registrationYear=" + registrationYear + ", registrationState=" + registrationState + ", customer="
				+ customer + "]";
	}
	
}
