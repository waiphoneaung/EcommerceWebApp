package com.g2.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
//	User findByUsername(String username);
	Optional<User> findByName(String name);
}
