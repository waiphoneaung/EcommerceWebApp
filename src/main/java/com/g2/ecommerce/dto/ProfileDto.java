package com.g2.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ProfileDto {
	@Email
	private String email;
	
	@Size(min = 7, message = "Phone Number must be at least 7 numbers.")
	@Size(max = 15, message = "Phone Number must not be exceeded than 15 numbers.")
	private String phone_number;
	
	@Size(min = 15, message = "Address must be above 15 characters.")
	private String address;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public ProfileDto() {
		
	}
}
