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

import com.g2.ecommerce.dto.DeliveryDto;
import com.g2.ecommerce.model.Delivery;
import com.g2.ecommerce.service.DeliveryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard/delivery")
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping({"","/"})
	public String showAllDeliveries(Model model) {
		List<Delivery> deliveries = deliveryService.getAllDeliveries();
		model.addAttribute("deliveries", deliveries);
		return "admin/deliveryList";
	}
	
	@GetMapping("/add")
	public String createDelivery(Model model) {
		DeliveryDto deliveryDto = new DeliveryDto();
		model.addAttribute("deliveryDto", deliveryDto);
		return "admin/addDelivery";
	}
	
	@PostMapping("/add")
	public String saveDelivery(@Valid @ModelAttribute("deliveryDto") DeliveryDto deliveryDto, BindingResult br) {
		if (br.hasErrors()) {
			return "admin/addDelivery";
		}
		deliveryService.createDelivery(deliveryDto);
		return "redirect:/adminDashboard/delivery";
	}
	
	@GetMapping("/edit")
	public String editDelivery(Model model, @RequestParam("id") int id) {
		Delivery delivery = deliveryService.getDeliveryById(id);
		DeliveryDto deliveryDto = new DeliveryDto();
		deliveryDto.setCompany_name(delivery.getName());
		deliveryDto.setPhone(delivery.getPhone());
		
		model.addAttribute("deliveryDto", deliveryDto);
		return "admin/editDelivery";
	}
	
	@PostMapping("/edit")
	public String saveEditedDelivery(@Valid @ModelAttribute("deliveryDto") DeliveryDto deliveryDto, BindingResult br, @RequestParam("id")int id) {
		if (br.hasErrors()) {
			return "admin/editDelivery";
		}
		
		deliveryService.editDelivery(id, deliveryDto);
		return "redirect:/adminDashboard/delivery";
	}
	
	@GetMapping("/delete")
	public String deleteDelivery(@RequestParam("id") int id) {
		deliveryService.deleteDelivery(id);
		return "redirect:/adminDashboard/delivery";
	}
}
