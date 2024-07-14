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

import com.g2.ecommerce.dto.AddressDto;
import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.service.AddressService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping({"","/"})
	public String allAddresses(Model model) {
		List<Address> addresses = addressService.getAllAddresses();
		model.addAttribute("addresses", addresses);
		return "admin/addressList";
	}
	
	@GetMapping("/add")
	public String createAddress(Model model) {
		AddressDto addressDto = new AddressDto();
		model.addAttribute("addressDto", addressDto);
		return "admin/addAddress";
	}
	
	@PostMapping("/add")
	public String saveAddress(@Valid @ModelAttribute("addressDto") AddressDto addressDto, BindingResult br) {
		if (br.hasErrors()) {
			return "admin/addAddress";
		}
		addressService.createAddress(addressDto);
		return "redirect:/adminDashboard/address";
	}
	
	@GetMapping("/edit")
	public String editAddress(Model model, @RequestParam("id") int id) {
		Address address = addressService.getAddressById(id);
		AddressDto addressDto = new AddressDto();
		addressDto.setTownship(address.getAddress());
		addressDto.setDeli_fees(address.getDeli_fees());
		addressDto.setWaiting_time(address.getWaiting_time());
		
		model.addAttribute("addressDto", addressDto);
		return "admin/editAddress";
	}
	
	@PostMapping("/edit")
	public String saveEditedAddress(@Valid @ModelAttribute("addressDto") AddressDto addressDto,BindingResult br,@RequestParam("id") int id) {
		if (br.hasErrors()) {
			return "admin/editAddress";
		}
		
		addressService.editAddress(id, addressDto);
		return "redirect:/adminDashboard/address";
	}
	
	@GetMapping("/delete")
	public String deleteAddress(@RequestParam("id")int id) {
		addressService.deleteAddress(id);
		return "redirect:/adminDashboard/address";
	}
}
