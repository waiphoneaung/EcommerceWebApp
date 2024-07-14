package com.g2.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import com.g2.ecommerce.model.Category;

import jakarta.validation.constraints.*;

public class ProductDto {
	@NotEmpty(message = "Product name cannot be a blank.")
	private String name;

	private Category category;

	@NotEmpty(message = "Product brand cannot be a blank.")
	private String brand;

	@Min(0)
	private double price;

	@Min(1)
	private int quantity;

	@Size(min = 10, message = "The description should be at least 10 characters")
	@Size(max = 1000, message = "The description should not exceed than 1000 characters")
	private String description;

	private MultipartFile ProductImageFile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public MultipartFile getProductImageFile() {
		return ProductImageFile;
	}

	public void setProductImageFile(MultipartFile productImageFile) {
		ProductImageFile = productImageFile;
	}

	public ProductDto() {

	}

	public void setImageFileName(String imageName) {
		// TODO Auto-generated method stub
		
	}
}
