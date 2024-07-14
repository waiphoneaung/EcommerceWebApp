package com.g2.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.g2.ecommerce.dto.ProductDto;
import com.g2.ecommerce.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Product getProductById(int product_id);
	long getProductCount();
	void addProduct(ProductDto productDto);
	Optional<Product> detailsProduct(int id);
	void editProduct(int product_id, ProductDto productDto);
	void deleteProduct(int id);
}