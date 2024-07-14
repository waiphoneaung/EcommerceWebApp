package com.g2.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DeliveryDto {
	@NotEmpty(message = "")
	private String company_name;
	
	@Size(min = 7, message = "Phone number must be at least 7 numbers.")
	@Size(max = 15, message = "Phone number must not exceeded than 15 numbers.")
	private String phone;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public DeliveryDto() {
		
	}
}
