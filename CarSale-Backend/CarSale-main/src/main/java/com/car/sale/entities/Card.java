package com.car.sale.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cardId;

	@NotEmpty(message = "Card Name cannot be empty")
	private String cardName;

	@NotNull(message = "Card number is empty")
	@Pattern(regexp = "^([1-9][0-9]{11})$", message = "Invalid card number")
	private String cardNumber;

	@JsonFormat(pattern = "MM/yy")
	@NotNull(message = "Card Expiry cannot be empty")
	private Date cardExpiry;

	@NotEmpty(message = "Bank Name cannot be empty")
	private String bankName;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Payment> payment;

	public Card() {

	}

	public Card(long cardId, String cardName, String cardNumber, Date cardExpiry, String bankName,
			List<Payment> payment) {
		super();
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cardExpiry = cardExpiry;
		this.bankName = bankName;
		this.payment = payment;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(Date cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

}
