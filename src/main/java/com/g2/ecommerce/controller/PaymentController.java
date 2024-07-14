package com.g2.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.ecommerce.model.Payment;
import com.g2.ecommerce.service.PaymentService;

@Controller
@RequestMapping("/adminDashboard/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping({"","/"})
	public String viewPaymentHistory(Model model) {
		List<Payment> payments = paymentService.getAllPayments();
		model.addAttribute("payments", payments);
		return "admin/payments";
	}
	
	@GetMapping("/deletePaymentHistory")
	public String deletePaymentHistory(@RequestParam int id) {
		paymentService.deletePayment(id);
		return "redirect:/adminDashboard/payments";
	}
}
