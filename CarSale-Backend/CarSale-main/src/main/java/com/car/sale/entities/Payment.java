package com.car.sale.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long paymentId;

	@NotEmpty(message = "Type cannot be Empty")
	@Pattern(regexp = "DC|CC", message = "Type should be CC or DC")
	private String type;

	@NotEmpty(message = "Status cannot be Empty")
	private String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "card_id", referencedColumnName = "cardId")
	@NotNull(message = "Enter card details")
	@JsonBackReference
	@Valid
	private Card card;

	public Payment() {

	}

	public Payment(long paymentId, String type, String status, Card card) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.card = card;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
