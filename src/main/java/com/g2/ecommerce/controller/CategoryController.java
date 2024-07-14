package com.g2.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.ecommerce.dto.CategoryDto;
import com.g2.ecommerce.model.Category;
import com.g2.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping({"","/"})
	public String allCategories(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		return "admin/categoryList";
	}
	
	@GetMapping("/add")
	public String createCategory(Model model) {
		CategoryDto categoryDto = new CategoryDto();
		model.addAttribute("categoryDto", categoryDto);
		return "admin/addCategory";
	}
	
	@PostMapping("/add")
	public String saveCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult br) {
		if (br.hasErrors()) {
			return "admin/addCategory";
		}
		categoryService.addCategory(categoryDto);
		return "redirect:/adminDashboard/categories";
	}
	
	@GetMapping("/edit")
	public String editCategory(Model model,@RequestParam("id") int id) {
		Category category = categoryService.getCategoryById(id);
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(category.getCategory_name());
		
		model.addAttribute("categoryDto", categoryDto);
		return "admin/editCategory";
	}
	
	@PostMapping("/edit")
	public String saveCategory(Model model,@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult br,
						@RequestParam("id") int id) {
		if (br.hasErrors()) {
			return "admin/editCategory";
		}
		
		categoryService.editCategory(id, categoryDto);
		return "redirect:/adminDashboard/categories";
	}
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam("id") int id) {
		categoryService.deleteCategory(id);
		return "redirect:/adminDashboard/categories";
	}
}
