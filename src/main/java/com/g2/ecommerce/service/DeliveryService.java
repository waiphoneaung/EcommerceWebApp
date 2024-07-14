package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.DeliveryDto;
import com.g2.ecommerce.model.Delivery;

public interface DeliveryService {
	List<Delivery> getAllDeliveries();
	void saveDelivery(Delivery delivery);
	Delivery getDeliveryById(int id);
	void createDelivery(DeliveryDto deliveryDto);
	void editDelivery(int delivery_id, DeliveryDto deliveryDto);
	void deleteDelivery(int delivery_id);
}
