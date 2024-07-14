package com.g2.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.ecommerce.dto.PaymentTypeDto;
import com.g2.ecommerce.model.PaymentType;
import com.g2.ecommerce.service.PaymentTypeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard/paymentType")
public class PaymentTypeController {
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@GetMapping({"","/"})
	public String viewPaymentTypeList(Model model) {
		List<PaymentType> paymentTypes = paymentTypeService.getAllPaymentTypes();
		model.addAttribute("paymentTypes", paymentTypes);
		return "admin/paymentTypes";
	}
	
	@GetMapping("/create")
	public String createPayType(Model model) {
		PaymentTypeDto payTypeDto = new PaymentTypeDto();
		model.addAttribute("payTypeDto", payTypeDto);
		return "admin/addPaymentType";
	}
	
	@PostMapping("/create")
	public String savePayType(Model model, @Valid @ModelAttribute("payTypeDto") PaymentTypeDto paymentTypeDto, BindingResult br) {
		if (br.hasErrors()) {
			return "admin/addPaymentType";
		}
		paymentTypeService.createPaymentType(paymentTypeDto);
		return "redirect:/adminDashboard/paymentType";
	}
	
	@GetMapping("/edit")
	public String editPayType(@RequestParam int id,Model model) {
		PaymentType payType = paymentTypeService.getPaymentTypeById(id);
		PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
		paymentTypeDto.setPayment_type(payType.getPayment_type());
		
		model.addAttribute("paymentTypeDto", paymentTypeDto);
		return "admin/editPaymentType";
	}
	
	@PostMapping("/edit")
	public String saveEditedPayType(@RequestParam int id, @Valid @ModelAttribute("paymentTypeDto") PaymentTypeDto paymentTypeDto,BindingResult br) {
		if (br.hasErrors()) {
			return "admin/editPaymentType";
		}
		
		paymentTypeService.editPaymentType(id, paymentTypeDto);
		return "redirect:/adminDashboard/paymentType";
	}
	
	@GetMapping("/delete")
	public String deletePayType(@RequestParam int id) {
		paymentTypeService.deletePaymentType(id);
		return "redirect:/adminDashboard/paymentType";
	}
}
