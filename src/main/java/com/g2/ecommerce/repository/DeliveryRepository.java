package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {

}
