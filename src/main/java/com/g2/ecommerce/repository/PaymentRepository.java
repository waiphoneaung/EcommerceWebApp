package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
