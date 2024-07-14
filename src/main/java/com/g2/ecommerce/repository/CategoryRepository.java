package com.g2.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g2.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
