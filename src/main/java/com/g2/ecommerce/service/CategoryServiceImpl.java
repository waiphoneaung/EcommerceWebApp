package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.CategoryDto;
import com.g2.ecommerce.model.Category;
import com.g2.ecommerce.model.Product;
import com.g2.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	@Lazy
	private ProductService productService;
	
	@Override
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	@Override
	public void addCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategory_name(categoryDto.getName());
		categoryRepository.save(category);
	}
	
	@Override
	public Category getCategoryById(int category_id) {
		return categoryRepository.findById(category_id).orElse(null);
	}
	
	@Override
	public void editCategory(int category_id, CategoryDto categoryDto) {
		Category category = getCategoryById(category_id);
		
		category.setCategory_name(categoryDto.getName());
		
		categoryRepository.save(category);
	}
	
	@Override
	public void deleteCategory(int category_id) {
		Category category = getCategoryById(category_id);
		
		if (category != null) {
			List<Product> products = productService.getAllProducts();
			for (Product product : products) {
				if (product.getCategory().getCategory_name().equals(category.getCategory_name())) {
					product.setCategory(null);
				}
			}
		}
		categoryRepository.delete(category);
	}
	
}
