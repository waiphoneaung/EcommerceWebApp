package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Integer>{
//	Profile findByEmail(String email);
}
