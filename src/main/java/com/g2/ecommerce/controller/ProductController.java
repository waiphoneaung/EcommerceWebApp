package com.g2.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.g2.ecommerce.dto.ProductDto;
import com.g2.ecommerce.model.Category;
import com.g2.ecommerce.model.Product;
import com.g2.ecommerce.service.CategoryService;
import com.g2.ecommerce.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping({ "", "/" })
	public String allProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "admin/productList";
	}

	@GetMapping("/add")
	public String createProduct(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		ProductDto productDto = new ProductDto();
		model.addAttribute("categories", categories);
		model.addAttribute("productDto", productDto);
		return "admin/addProductForm";
	}

	@PostMapping("/add")
	public String saveProduct(Model model,@RequestParam("ProductImageFile") MultipartFile imageFile,
			@Valid @ModelAttribute("productDto") ProductDto productDto, BindingResult br) {

		if (productDto.getProductImageFile().isEmpty()) {
			br.addError(new FieldError("productDto", "imageFile", "The image is required."));
		}
		if (br.hasErrors()) {
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);
			return "admin/addProductForm";
		}
		productDto.setProductImageFile(imageFile);
		productService.addProduct(productDto);
		return "redirect:/adminDashboard/products";
	}

	@GetMapping("/edit")
	public String showEditForm(@RequestParam("id") int id, Model model) {
		Product product = productService.getProductById(id);
		ProductDto productDto = new ProductDto();
		productDto.setName(product.getProduct_name());
		productDto.setBrand(product.getProduct_brand());
		productDto.setCategory(product.getCategory());
		productDto.setPrice(product.getPrice());
		productDto.setQuantity(product.getProduct_qty());
		productDto.setDescription(product.getDescription());
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("productDto", productDto);
		return "admin/editProduct";
	}

	@PostMapping("/edit")
	public String updateProduct(Model model, @Valid @ModelAttribute("productDto") ProductDto productDto,
			BindingResult result, @RequestParam("id") int id, @RequestParam("ProductImageFile") MultipartFile image) {
		if (result.hasErrors()) {
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);
			return "admin/editProduct";
		}

		if (!image.isEmpty()) {
			productDto.setProductImageFile(image);
		} else {
			productDto.setProductImageFile(null);
		}
		
		productService.editProduct(id, productDto);
		return "redirect:/adminDashboard/products";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam int id) {
		productService.deleteProduct(id);
		return "redirect:/adminDashboard/products";
	}
}
	