package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.CategoryDto;
import com.g2.ecommerce.model.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	void addCategory(CategoryDto categoryDto);
	Category getCategoryById(int category_id);
	void editCategory(int category_id,CategoryDto categoryDto);
	void deleteCategory(int category_id);
}
