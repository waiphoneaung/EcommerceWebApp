package com.g2.ecommerce.service;

import java.util.List;

import com.g2.ecommerce.model.Payment;

public interface PaymentService {
	List<Payment> getAllPayments();
	Payment getPaymentById(int id);
	void savePayment (int paymentType_id,int order_id);
	void deletePayment(int id);
}
