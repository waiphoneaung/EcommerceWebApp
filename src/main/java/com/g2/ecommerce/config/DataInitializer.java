package com.g2.ecommerce.config;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.g2.ecommerce.service.RoleService;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
	private final RoleService roleService;
	
	public DataInitializer(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostConstruct
	public void init() throws Exception {
		roleService.initializeRoles(Arrays.asList("ADMIN", "USER"));
	}
}
