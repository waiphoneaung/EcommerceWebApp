package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.model.Delivery;

public interface DeliInfoRepository extends JpaRepository<DeliInfo,Integer>{
	DeliInfo findByAddressAndDelivery(Address address, Delivery delivery);
}
