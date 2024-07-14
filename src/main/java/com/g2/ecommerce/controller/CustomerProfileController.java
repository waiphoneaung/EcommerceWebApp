package com.g2.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.ecommerce.dto.ProfileDto;
import com.g2.ecommerce.model.Profile;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.security.LoginUserDetail;
import com.g2.ecommerce.service.ProfileService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer/profile")
public class CustomerProfileController {
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/view")
	public String viewProfile(@RequestParam int id, Model model, HttpSession session,Authentication authentication) {
		LoginUserDetail userDetail = (LoginUserDetail)authentication.getPrincipal();
		User user = userDetail.getUser();
		int user_id = user.getId();
		List<Profile> profiles = profileService.getAllProfiles();
		
		int profile_id = 0;
		for (Profile profile : profiles) {
			if (profile.getUser().getId() == user_id) {
				profile_id = profile.getId();
			}
		}
		Profile profile = profileService.getProfileById(id);
		model.addAttribute("profile", profile);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/userProfile";
	}
	
	@GetMapping("/edit")
	public String editProfile(@RequestParam("id") int id, Model model, HttpSession session,Authentication authentication) {
		LoginUserDetail userDetail = (LoginUserDetail)authentication.getPrincipal();
		User user = userDetail.getUser();
		int user_id = user.getId();
		List<Profile> profiles = profileService.getAllProfiles();
		
		int profile_id = 0;
		for (Profile profile : profiles) {
			if (profile.getUser().getId() == user_id) {
				profile_id = profile.getId();
			}
		}
		Profile profile = profileService.getProfileById(id);
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(profile.getEmail());
		profileDto.setAddress(profile.getAddress());
		profileDto.setPhone_number(profile.getPhone());
		model.addAttribute("profileDto", profileDto);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/editProfile";
	}
	
	@PostMapping("/edit")
	public String saveProfile(@RequestParam("id") int id,@Valid @ModelAttribute("profileDto") ProfileDto profileDto, BindingResult br) {
		if (br.hasErrors()) {
			return "customer/editProfile";
		}
		profileService.editProfile(id, profileDto);
		return "redirect:/customer/profile/view?id=" + id;
	}
}
