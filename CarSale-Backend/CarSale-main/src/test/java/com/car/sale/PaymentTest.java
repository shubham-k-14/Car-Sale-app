package com.car.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.car.sale.entities.Card;
import com.car.sale.entities.Payment;
import com.car.sale.exception.PaymentExceptions;
import com.car.sale.repository.PaymentRepository;
import com.car.sale.service.PaymentService;

@SpringBootTest
public class PaymentTest {

	@Autowired
	private PaymentService paymentService;

	@MockBean
	PaymentRepository paymentRepository;

	public PaymentTest() {

	}

	// test case for adding payment
	@Test
	public void testAddPayment() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.save(payment)).thenReturn(payment);
		try {
			assertEquals(paymentService.addPayment(payment), payment);
		} catch (PaymentExceptions e) {
			assertTrue(false);
		}
	}

	// test case for updating payment
	@Test
	public void testUpdatePayment() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.findById((long) 1)).thenReturn(Optional.of(payment));
		payment.setType("DC");
		when(paymentRepository.save(payment)).thenReturn(payment);
		try {
			assertEquals(paymentService.updatePayment(1, payment), payment);
		} catch (PaymentExceptions e) {
			assertTrue(false);
		}
	}

	// test case for removing payment
	@Test
	public void testRemovePayment() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.findById((long) 1)).thenReturn(Optional.of(payment));
		try {
			paymentService.removePayment(1);
			verify(paymentRepository, times(1)).deleteById((long) 1);
		} catch (PaymentExceptions e) {
			assertTrue(false);
		}
	}

	// test case for displaying payment details by id
	@Test
	public void testGetPayment() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.findById((long) 1)).thenReturn(Optional.of(payment));
		try {
			assertEquals(paymentService.getPaymentDetails(1), payment);
		} catch (PaymentExceptions e) {
			assertTrue(false);
		}
	}

	// test case for displaying all the payment details
	@Test
	public void testGetAllPayment() throws ParseException {
		List<Payment> paymentList = new ArrayList<>();
		paymentList.add(getPayment());
		when(paymentRepository.findAll()).thenReturn(paymentList);
		try {
			assertEquals(paymentService.getAllPaymentDetails(), paymentList);
		} catch (PaymentExceptions e) {
			assertTrue(false);
		}
	}

	// Exception testing to get payment details by id
	@Test
	public void testGetPaymentException() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.findById((long) 1)).thenReturn(Optional.of(payment));
		assertThrows(PaymentExceptions.class, () -> paymentService.getPaymentDetails(2));
	}

	// Exception testing to update payment details
	@Test
	public void testUpdatePaymentException() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.findById((long) 2)).thenReturn(Optional.of(payment));
		payment.setStatus("Failed");
		when(paymentRepository.save(payment)).thenReturn(payment);
		assertThrows(PaymentExceptions.class, () -> paymentService.updatePayment(1, payment));

	}

	// Exception testing to remove payment details
	@Test
	public void testRemovePaymentException() throws ParseException {
		Payment payment = getPayment();
		when(paymentRepository.findById((long) 2)).thenReturn(Optional.of(payment));
		assertThrows(PaymentExceptions.class, () -> paymentService.removePayment(1));
	}

	private Card getCard() throws ParseException {
		Card card = new Card();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		Date date = simpleDateFormat.parse("03/22");
		card.setBankName("KVB");
		card.setCardExpiry(date);
		card.setCardId(1);
		card.setCardName("vihaan");
		card.setCardNumber("987432965039");
		return card;
	}

	private Payment getPayment() throws ParseException {
		Payment payment = new Payment();
		payment.setPaymentId(1);
		payment.setStatus("success");
		payment.setType("CC");
		payment.setCard(getCard());
		return payment;
	}

}
