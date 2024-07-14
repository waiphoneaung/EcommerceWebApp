package com.g2.ecommerce.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.model.Payment;
import com.g2.ecommerce.model.PaymentType;
import com.g2.ecommerce.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	@Lazy
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	@Lazy
	private OrdersService ordersService;
	
	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
	
	@Override
	public Payment getPaymentById(int id) {
		return paymentRepository.findById(id).orElse(null);
	}
	
	@Override
	public void savePayment(int paymentType_id, int order_id) {
		PaymentType paymentType = paymentTypeService.getPaymentTypeById(paymentType_id);
		Orders order = ordersService.getOrderById(order_id);
		Payment payment = new Payment();
		payment.setOrders(order);
		payment.setPaid_amount(order.getAmount());
		payment.setPaid_date(new Date());
		payment.setPayment_type(paymentType);
		
		paymentRepository.save(payment);
	}
	
	@Override
	public void deletePayment(int id) {
		Payment payment = getPaymentById(id);
		if (payment != null) {
			paymentRepository.delete(payment);
		}
	}
}
