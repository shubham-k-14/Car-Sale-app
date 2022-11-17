package com.car.sale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.car.sale.entities.Payment;
import com.car.sale.exception.PaymentExceptions;

@Service
public interface PaymentService {

	// to add payment details to the DB
	public Payment addPayment(Payment payment) throws PaymentExceptions;

	// to remove the payment details
	public Payment removePayment(long id) throws PaymentExceptions;

	// to update the payment details
	public Payment updatePayment(long id, Payment payment) throws PaymentExceptions;

	// to get the payment details by id
	public Payment getPaymentDetails(long id) throws PaymentExceptions;

	// to get all the payment details
	public List<Payment> getAllPaymentDetails() throws PaymentExceptions;

}
