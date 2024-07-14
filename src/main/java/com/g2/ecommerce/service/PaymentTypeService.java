package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.dto.PaymentTypeDto;
import com.g2.ecommerce.model.PaymentType;

public interface PaymentTypeService {
	List<PaymentType> getAllPaymentTypes();
	PaymentType getPaymentTypeById(int id);
	void createPaymentType(PaymentTypeDto paymentTypeDto);
	void editPaymentType(int id, PaymentTypeDto paymentTypeDto);
	void deletePaymentType(int id);
}
