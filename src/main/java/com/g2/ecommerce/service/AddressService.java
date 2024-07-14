package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.AddressDto;
import com.g2.ecommerce.model.Address;

public interface AddressService {
	List<Address> getAllAddresses();
	Address getAddressById(int id);
	void saveAddress(Address address);
	void createAddress(AddressDto addressDto);
	void editAddress(int address_id, AddressDto addressDto);
	void deleteAddress(int address_id);
}
