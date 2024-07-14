package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.OrderHistory;
import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.repository.OrderHistoryRepository;
import com.g2.ecommerce.repository.OrdersRepository;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	@Autowired
	private OrderHistoryRepository orderHistoryRepo;

	@Autowired
	private OrdersRepository orderRepo;

	@Override
	public List<OrderHistory> getAllOrderHistories() {
		return orderHistoryRepo.findAll();
	}

	@Override
	public OrderHistory getOrderHistoryById(int id) {
		return orderHistoryRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteOrderHistory(int id) {
		OrderHistory orderHistory = getOrderHistoryById(id);
		Orders order = orderHistory.getOrder();
		if (orderHistory != null) {
			orderHistoryRepo.delete(orderHistory);
			if (orderHistoryRepo.findByOrder(order).isEmpty()) {
				orderRepo.delete(order);
			}
		}
	}
}
