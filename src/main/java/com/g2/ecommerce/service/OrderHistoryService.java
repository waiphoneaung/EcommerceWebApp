package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.model.OrderHistory;

public interface OrderHistoryService {
	List<OrderHistory> getAllOrderHistories();
	OrderHistory getOrderHistoryById(int id);
	void deleteOrderHistory(int id);
}
