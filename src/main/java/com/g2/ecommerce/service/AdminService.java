package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.model.User;

public interface AdminService {
	List<User> getAllUsers();
	void saveAssigningAddressAndDelivery(int address_id, int delivery_id);
}
