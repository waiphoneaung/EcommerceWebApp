package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
