package com.car.sale.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.sale.entities.Payment;
import com.car.sale.exception.PaymentExceptions;
import com.car.sale.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public PaymentServiceImpl() {
		super();
	}

	// to add payment to the DB
	@Override
	public Payment addPayment(Payment payment) throws PaymentExceptions {
		Payment p = paymentRepository.save(payment);
		if (p == null)
			throw new PaymentExceptions("Payment details not added");
		return p;
	}

	// to remove payment from the DB
	@Override
	public Payment removePayment(long id) throws PaymentExceptions {
		Optional<Payment> p = paymentRepository.findById(id);
		if (p.isPresent()) {
			paymentRepository.deleteById(id);
		} else
			throw new PaymentExceptions("Payment Details does not exist");
		return p.get();
	}

	// to update the payment details
	@Override
	public Payment updatePayment(long id, Payment payment) throws PaymentExceptions {
		Optional<Payment> p = paymentRepository.findById(id);
		if (p.isPresent()) {
			paymentRepository.save(payment);
		} else
			throw new PaymentExceptions("Payment Details not Found");
		return payment;
	}

	// to get the payment details by id
	@Override
	public Payment getPaymentDetails(long id) throws PaymentExceptions {
		Payment payment;
		Optional<Payment> p = paymentRepository.findById(id);
		if (p.isPresent()) {
			payment = p.get();
		} else
			throw new PaymentExceptions("Payment ID not Found");
		return payment;
	}

	// to get all the payment details from the DB
	@Override
	public List<Payment> getAllPaymentDetails() throws PaymentExceptions {
		List<Payment> paymentList = paymentRepository.findAll();
		if (paymentList.size() == 0)
			throw new PaymentExceptions("No Records Found");
		return paymentList;
	}

}
