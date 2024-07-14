package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.AddressDto;
import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	@Lazy
	private DeliInfoService deliInfoService;

	@Override
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressById(int id) {
		return addressRepository.findById(id).orElse(null);
	}
	
	@Override
	public void saveAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void createAddress(AddressDto addressDto) {
		Address address = new Address();
		address.setAddress(addressDto.getTownship());
		address.setDeli_fees(addressDto.getDeli_fees());
		address.setWaiting_time(addressDto.getWaiting_time());
		addressRepository.save(address);
	}

	@Override
	public void editAddress(int address_id, AddressDto addressDto) {
		Address address = getAddressById(address_id);

		if (address != null) {
			address.setAddress(addressDto.getTownship());
			address.setDeli_fees(addressDto.getDeli_fees());
			address.setWaiting_time(address.getWaiting_time());

			addressRepository.save(address);
		}

	}
	
	@Override
	public void deleteAddress(int address_id) {
		Address address = getAddressById(address_id);
		
		if (address != null) {
			List<DeliInfo> deliInfos = deliInfoService.getAllDeliInfos();
			for (DeliInfo deli : deliInfos) {
				if (deli.getAddress() != null && deli.getAddress().equals(address)) {
                    deli.setAddress(null); 
                    deliInfoService.saveDeliInfo(deli); 
                }
			}
			addressRepository.delete(address);
		}
	}
}
