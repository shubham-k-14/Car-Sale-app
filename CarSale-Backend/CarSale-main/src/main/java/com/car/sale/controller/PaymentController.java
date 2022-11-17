package com.car.sale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.entities.Payment;
import com.car.sale.exception.PaymentExceptions;
import com.car.sale.service.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	public PaymentController() {

	}

	// To save the payments in the database
	@PostMapping(value = "/addpayment")
	public ResponseEntity<Object> addPayment(@Valid @RequestBody Payment payment, BindingResult result) {
		try {
			String error = "";
			if (result.hasErrors()) {
				for (ObjectError err : result.getAllErrors()) {
					error = error + "\n" + err.getDefaultMessage();
				}
				return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
			}
			Payment paymentResponse = paymentService.addPayment(payment);
			ResponseEntity<Object> re = new ResponseEntity<Object>(paymentResponse, HttpStatus.OK);
			return re;
		} catch (PaymentExceptions pe) {
			ResponseEntity<Object> re = new ResponseEntity<Object>(pe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return re;
		}
	}

	// to delete the payment in the database by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removePayment(@PathVariable long id) {
		try {
			Payment payment = paymentService.removePayment(id);
			return new ResponseEntity<Object>(payment, HttpStatus.FOUND);
		} catch (PaymentExceptions pe) {
			return new ResponseEntity<Object>(pe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// To update the payment in the database
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updatePayment(@PathVariable long id, @RequestBody @Valid Payment payment,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				String error = "";
				for (ObjectError err : result.getAllErrors()) {
					error = error + "\n" + err.getDefaultMessage();
				}
				return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
			}
			Payment p = paymentService.updatePayment(id, payment);
			return new ResponseEntity<Object>(p, HttpStatus.OK);
		} catch (PaymentExceptions pe) {
			ResponseEntity<Object> re = new ResponseEntity<Object>(pe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return re;
		}
	}

	// To get the payment details by id from the database
	@RequestMapping("/getpayment/{id}")
	public ResponseEntity<Object> getPaymentDetails(@PathVariable long id) {
		Payment payment;
		try {
			payment = paymentService.getPaymentDetails(id);
			ResponseEntity<Object> re = new ResponseEntity<Object>(payment, HttpStatus.OK);
			return re;
		} catch (PaymentExceptions pe) {
			ResponseEntity<Object> re = new ResponseEntity<Object>(pe.getMessage(), HttpStatus.NOT_FOUND);
			return re;
		}
	}

	// to get all the payment details from the database
	@GetMapping("/getallpayment")
	public ResponseEntity<Object> getAllPaymentDetails() {
		List<Payment> paymentList;
		try {
			paymentList = paymentService.getAllPaymentDetails();
			return new ResponseEntity<Object>(paymentList, HttpStatus.OK);
		} catch (PaymentExceptions pe) {
			ResponseEntity<Object> re = new ResponseEntity<Object>(pe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return re;
		}

	}
}
