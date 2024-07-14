package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.model.Delivery;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.repository.DeliInfoRepository;
import com.g2.ecommerce.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private DeliInfoRepository deliInfoRepository;
	
	@Override
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public void saveAssigningAddressAndDelivery(int address_id, int delivery_id) {
		Address address = addressService.getAddressById(address_id);
		Delivery delivery = deliveryService.getDeliveryById(delivery_id);
		
		if (address != null && delivery != null) {
			DeliInfo deliInfo = new DeliInfo();
			deliInfo.setAddress(address);
			deliInfo.setDelivery(delivery);
			
			deliInfoRepository.save(deliInfo);
		}
	}
}

