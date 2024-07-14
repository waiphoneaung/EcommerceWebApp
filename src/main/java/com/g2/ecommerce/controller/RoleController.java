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

import com.g2.ecommerce.dto.RoleDto;
import com.g2.ecommerce.model.Role;
import com.g2.ecommerce.service.RoleService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping({"","/"})
	public String viewRoles(Model model) {
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("roles", roles);
		return "admin/roleList";
	}
	
	@GetMapping("/add")
	public String addRole(Model model) {
		RoleDto roleDto = new RoleDto();
		model.addAttribute("roleDto", roleDto);
		return "admin/addRole";
	}
	
	@PostMapping("/add")
	public String saveRole(@Valid @ModelAttribute("roleDto") RoleDto roleDto,BindingResult br) {
		if (br.hasErrors()) {
			return "admin/addRole";
		}
		roleService.createRole(roleDto);
		return "redirect:/adminDashboard/roles";
	}
	
	@GetMapping("/edit")
	public String editRole(Model model,@RequestParam("id")int id) {
		Role role = roleService.getRoleById(id);
		RoleDto roleDto = new RoleDto();
		roleDto.setRole(role.getRole());
		model.addAttribute("roleDto", roleDto);
		return "admin/editRole";
	}
	
	@PostMapping("/edit")
	public String saveEditedRole(Model model, @Valid @ModelAttribute("roleDto") RoleDto roleDto, BindingResult br, 
						@RequestParam("id") int id) {
		if (br.hasErrors()) {
			return "admin/editRole";
		}
		
		roleService.editRole(id, roleDto);
		return "redirect:/adminDashboard/roles";
	}
	
	@GetMapping("/delete")
	public String deleteRole(@RequestParam("id") int id) {
		roleService.deleteRole(id);
		return "redirect:/adminDashboard/roles";
	}
}
