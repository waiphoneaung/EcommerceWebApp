package com.g2.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.model.Product;

import jakarta.servlet.http.HttpSession;

public interface CartService {
	void addToCart(Product product, HttpSession session);

	Map<Product, Integer> getCart(HttpSession session);

	void removeFromCart(int productId, HttpSession session);
	
	Orders saveOrder(int user_id,int address_id, List<Integer> quantities, HttpSession session, Orders order);
	
	void clearCart(HttpSession session);
}
