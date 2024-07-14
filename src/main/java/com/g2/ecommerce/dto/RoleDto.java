package com.g2.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

public class RoleDto {
	@NotEmpty(message = "Cannot be empty string.")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public RoleDto() {
		
	}
}
