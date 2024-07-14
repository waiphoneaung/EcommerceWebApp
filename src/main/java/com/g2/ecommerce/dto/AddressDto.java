package com.g2.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class AddressDto {
	@NotEmpty(message = "You should add at least one township name.")
	private String township;
	
	@Min(2500)
	private double deli_fees;
	
	@NotEmpty(message = "You should add `waiting time`.")
	private String waiting_time;

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public double getDeli_fees() {
		return deli_fees;
	}

	public void setDeli_fees(double deli_fees) {
		this.deli_fees = deli_fees;
	}
	
	public String getWaiting_time() {
		return waiting_time;
	}

	public void setWaiting_time(String waiting_time) {
		this.waiting_time = waiting_time;
	}

	public AddressDto() {
		
	}
}
