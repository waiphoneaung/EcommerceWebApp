package com.g2.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

public class PaymentTypeDto {
	@NotEmpty(message = "This field cannot be a blank.")
	private String payment_type;

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public PaymentTypeDto() {
		
	}
}
