package com.g2.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.dto.PaymentTypeDto;
import com.g2.ecommerce.model.PaymentType;
import com.g2.ecommerce.repository.PaymentTypeRepository;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Override
	public List<PaymentType> getAllPaymentTypes(){
		return paymentTypeRepository.findAll();
	}
	
	@Override
	public PaymentType getPaymentTypeById(int id) {
		return paymentTypeRepository.findById(id).orElse(null);
	}
	
	@Override
	public void createPaymentType(PaymentTypeDto paymentTypeDto) {
		PaymentType paymentType = new PaymentType();
		paymentType.setPayment_type(paymentTypeDto.getPayment_type());
		
		paymentTypeRepository.save(paymentType);
	}
	
	@Override
	public void editPaymentType(int id, PaymentTypeDto paymentTypeDto) {
		PaymentType paymentType = getPaymentTypeById(id);
		if (paymentType != null) {
			paymentType.setPayment_type(paymentTypeDto.getPayment_type());
			paymentTypeRepository.save(paymentType);
		}
	}
	
	@Override
	public void deletePaymentType(int id) {
		PaymentType paymentType = getPaymentTypeById(id);
		if (paymentType !=  null) {
			paymentTypeRepository.save(paymentType);
		}
	}
}
