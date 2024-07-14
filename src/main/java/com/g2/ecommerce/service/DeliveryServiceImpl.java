package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.DeliveryDto;
import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.model.Delivery;
import com.g2.ecommerce.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	@Lazy
	private DeliInfoService deliInfoService;
	
	@Override
	public List<Delivery> getAllDeliveries() {
		return deliveryRepository.findAll();
	}
	
	@Override
	public void saveDelivery(Delivery delivery) {
		deliveryRepository.save(delivery);
	}

	@Override
	public Delivery getDeliveryById(int id) {
		return deliveryRepository.findById(id).orElse(null);
	}

	@Override
	public void createDelivery(DeliveryDto deliveryDto) {
		Delivery delivery = new Delivery();
		delivery.setName(deliveryDto.getCompany_name());
		delivery.setPhone(deliveryDto.getPhone());
		deliveryRepository.save(delivery);
	}

	@Override
	public void editDelivery(int delivery_id, DeliveryDto deliveryDto) {
		Delivery delivery = getDeliveryById(delivery_id);
		if (delivery != null) {
			delivery.setName(deliveryDto.getCompany_name());
			delivery.setPhone(deliveryDto.getPhone());
			
			deliveryRepository.save(delivery);
		}
		
	}
	
	@Override
	public void deleteDelivery(int delivery_id) {
		Delivery delivery = getDeliveryById(delivery_id);
		if (delivery != null) {
			List<DeliInfo> deliInfos = deliInfoService.getAllDeliInfos();
			for (DeliInfo deli : deliInfos) {
				if (deli.getDelivery() != null && deli.getDelivery().equals(delivery)) {
                    deli.setDelivery(null); 
                    deliInfoService.saveDeliInfo(deli); 
                }
			}
			deliveryRepository.delete(delivery);
		}
	}
}
