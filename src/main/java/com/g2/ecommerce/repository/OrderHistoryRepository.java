package com.g2.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.OrderHistory;
import com.g2.ecommerce.model.Orders;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer>  {
	List<OrderHistory> findByOrder(Orders order);
}
