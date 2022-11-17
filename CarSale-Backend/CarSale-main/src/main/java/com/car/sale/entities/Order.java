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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity

@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long orderId;
	
	@Min(1000)
	private double amount;
	@NotNull(message =" null values not allowed")
	private LocalDate billingDate;
    @ManyToOne(cascade=CascadeType.ALL,fetch =FetchType.LAZY,optional=false)
    @JsonBackReference
    @Valid
    @JoinColumn(name="customer_id")
	private Customer customer;

    @Valid
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="payment_id")//, referencedColumnName="paymentId")
//	@NotNull
	private Payment payment;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(long orderId, @Min(1000) @Max(100000000) double amount,
			@NotNull(message = " null values not allowed") LocalDate billingDate, Customer customer,
			@NotNull Payment payment) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.billingDate = billingDate;
		this.customer = customer;
		this.payment = payment;
	}



	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate ) {
		this.billingDate = billingDate;
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
