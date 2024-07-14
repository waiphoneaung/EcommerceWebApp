package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public List<Orders> getAllOrders(){
		return ordersRepository.findAll();
	}
	
	@Override
	public Orders getOrderById(int order_id) {
		return ordersRepository.findById(order_id).orElse(null);
	}
	
	@Override
	public long getOrderCount() {
		return ordersRepository.count();
	}
}
