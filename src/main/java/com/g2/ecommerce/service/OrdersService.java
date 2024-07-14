package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.model.Orders;

public interface OrdersService {
	List<Orders> getAllOrders();
	Orders getOrderById(int order_id);
	long getOrderCount();
}
