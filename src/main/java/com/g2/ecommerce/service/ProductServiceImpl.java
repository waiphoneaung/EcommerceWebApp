package com.g2.ecommerce.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.g2.ecommerce.dto.ProductDto;
import com.g2.ecommerce.model.Product;
import com.g2.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(int product_id) {
		return productRepository.findById(product_id).orElse(null);
	}
	
	@Override
	public long getProductCount() {
		return productRepository.count();
	}
	
	@Override
	public void addProduct(ProductDto productDto) {
		Product product = new Product();
		product.setProduct_name(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setProduct_brand(productDto.getBrand());		
		product.setPrice(productDto.getPrice());
		product.setProduct_qty(productDto.getQuantity());
		product.setDescription(productDto.getDescription());
		
		MultipartFile multipartFile = productDto.getProductImageFile();
		String fileName = productDto.hashCode() + multipartFile.getOriginalFilename();
		product.setImageName(fileName);

		Path uploadPath = Paths.get("src/main/resources/static/images/");

		try {
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			Path filePath = uploadPath.resolve(fileName);
			Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		productRepository.save(product);
	}

	@Override
	public Optional<Product> detailsProduct(int id) {
		return productRepository.findById(id);
	}
	
	@Override
	public void editProduct(int product_id, ProductDto productDto) {
		Product product = getProductById(product_id);
        product.setProduct_name(productDto.getName());
        product.setProduct_brand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setProduct_qty(productDto.getQuantity());
        product.setDescription(productDto.getDescription());

        MultipartFile multipartFile = productDto.getProductImageFile();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = productDto.hashCode() + multipartFile.getOriginalFilename();
            product.setImageName(fileName);

            Path uploadPath = Paths.get("src/main/resources/static/images/");

            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productRepository.save(product);
	}
	
	@Override
	public void deleteProduct(int id) {
		Product product = getProductById(id);
		if (product != null) {
			productRepository.delete(product);
		}
	}
}
