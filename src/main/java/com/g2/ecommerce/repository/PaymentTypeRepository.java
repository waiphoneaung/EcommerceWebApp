package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType,Integer>{

}
