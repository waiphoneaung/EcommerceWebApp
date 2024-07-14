package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
