package com.g2.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {
	@NotEmpty(message = "Category name should not be a blank.")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
