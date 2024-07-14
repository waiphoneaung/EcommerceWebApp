package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

}
