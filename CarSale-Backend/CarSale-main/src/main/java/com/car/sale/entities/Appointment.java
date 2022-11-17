package com.car.sale.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import org.springframework.data.annotation.Id;

@Entity
@Table(name="appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long appointmentId;
	@NotEmpty
	@Size(min = 2,message = "Location should be atleast 5 letters")
	private String location;
	@NotEmpty
	@Pattern(regexp = "Available|Not Available", flags = Pattern.Flag.CASE_INSENSITIVE,message = "Please enter either Available or not Available")
	private String inspectionType;
//	@DateTimeFormat(pattern="yyyy.MM.dd")
	private LocalDate preferredDate;
//	@DateTimeFormat(pattern="HH.mm")
	private LocalTime preferredTime;
	@ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
	@JoinColumn(name = "user_Id")
	@JsonBackReference
	private Customer customer;
	@OneToOne(cascade=CascadeType.ALL)//(mappedBy = "appointment")
	@JoinColumn(name="payment_id")
//	@JsonIgnore
	private Payment payment;
	public Appointment() {
	}
	public Appointment(long appointmentId,
			@NotEmpty @Size(min = 2, message = "Location should be atleast 5 letters") String location,
			@NotEmpty @Pattern(regexp = "Available|Not Available", flags = Flag.CASE_INSENSITIVE, message = "Please enter either Available or not Available") String inspectionType,
			LocalDate preferredDate, LocalTime preferredTime, Customer customer, Payment payment) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.inspectionType = inspectionType;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.customer = customer;
		this.payment = payment;
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}
	public LocalDate getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(LocalDate preferredDate) {
		this.preferredDate = preferredDate;
	}
	public LocalTime getPreferredTime() {
		return preferredTime;
	}
	public void setPreferredTime(LocalTime preferredTime) {
		this.preferredTime = preferredTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}


}
