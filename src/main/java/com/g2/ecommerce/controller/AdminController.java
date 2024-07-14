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

import com.g2.ecommerce.dto.ProfileDto;
import com.g2.ecommerce.dto.UserDto;
import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.model.Delivery;
import com.g2.ecommerce.model.OrderHistory;
import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.model.Role;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.service.AddressService;
import com.g2.ecommerce.service.AdminService;
import com.g2.ecommerce.service.DeliInfoService;
import com.g2.ecommerce.service.DeliveryService;
import com.g2.ecommerce.service.OrderHistoryService;
import com.g2.ecommerce.service.OrdersService;
import com.g2.ecommerce.service.ProductService;
import com.g2.ecommerce.service.RoleService;
import com.g2.ecommerce.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/adminDashboard")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private DeliInfoService deliInfoService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderHistoryService orderHistoryService;

	@GetMapping({ "", "/" })
	public String dashBoard(Model model) {
		long userCount = userService.getUserCount();
		long productCount = productService.getProductCount();
		long orderCount = ordersService.getOrderCount();
		model.addAttribute("userCount", userCount);
		model.addAttribute("productCount", productCount);
		model.addAttribute("orderCount", orderCount);
		return "admin/admindashboard1";
	}

	@GetMapping("/viewUser")
	public String viewCustomerList(Model model) {
		List<User> users = adminService.getAllUsers();
		model.addAttribute("users", users);
		return "admin/userList";
	}

	@GetMapping("/addNewCustomer")
	public String loginCustomer(Model model) {
		UserDto userDto = new UserDto();
		ProfileDto profileDto = new ProfileDto();
		userDto.setProfileDto(profileDto);
		model.addAttribute("userDto", userDto);
		return "admin/registerationForm";
	}

	@PostMapping("/addNewCustomer")
	public String saveCustomer(Model model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult br) {
		if (br.hasErrors()) {
			return "admin/registerationForm";
		}
		userService.registerCustomer(userDto, userDto.getProfileDto());
		return "redirect:/adminDashboard/assignRole";
	}

	@GetMapping("/editUser")
	public String editCustomer(Model model, @RequestParam("id") int id) {
		User user = userService.getUserById(id);
		UserDto userDto = new UserDto();
		ProfileDto profileDto = new ProfileDto();
		userDto.setUsername(user.getName());
		profileDto.setAddress(user.getProfile().getAddress());
		profileDto.setEmail(user.getProfile().getEmail());
		profileDto.setPhone_number(user.getProfile().getPhone());

		userDto.setProfileDto(profileDto);
		model.addAttribute("userDto", userDto);
		return "admin/editUser";
	}

	@PostMapping("/editUser")
	public String saveCustomer(Model model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult br,
			@RequestParam("id") int id) {
		if (br.hasErrors()) {
			return "admin/editUser";
		}
		userService.editUser(id, userDto);
		return "redirect:/adminDashboard/viewUser";
	}

	@GetMapping("/deleteUser")
	public String deleteCustomer(@RequestParam("id") int id) {
		userService.deleteUser(id);
		return "redirect:/adminDashboard/viewUser";
	}

	@GetMapping("/assignRole")
	public String assignRole(Model model) {
		List<User> users = userService.getAllUsers();
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "admin/roleAssign";
	}

	@PostMapping("/assignRole")
	public String saveUserRole(@RequestParam int user_id, @RequestParam int role_id) {
		userService.saveRole(role_id, user_id);
		return "redirect:/adminDashboard/viewUser";
	}

	@GetMapping("/assignAddressAndDelivery")
	public String assignAddressAndDelivery(Model model) {
		List<Address> addresses = addressService.getAllAddresses();
		List<Delivery> deliveries = deliveryService.getAllDeliveries();
		model.addAttribute("addresses", addresses);
		model.addAttribute("deliveries", deliveries);
		return "admin/assignDeliveryAndAddress";
	}

	@PostMapping("/assignAddressAndDelivery")
	public String saveAddressAndDeliveryAssigning(@RequestParam int address_id, @RequestParam int delivery_id) {
		adminService.saveAssigningAddressAndDelivery(address_id, delivery_id);
		return "redirect:/adminDashboard/DeliveryAddressInfo";
	}

	@GetMapping("/DeliveryAddressInfo")
	public String viewDeliAddInfo(Model model) {
		List<DeliInfo> deliInfos = deliInfoService.getAllDeliInfos();
		model.addAttribute("deliInfos", deliInfos);
		return "admin/deliveryAddressInfo";
	}

	@GetMapping("/deleteDeliInfo")
	public String deleteDeliInfo(@RequestParam("id")int id) {
		deliInfoService.deleteDeliInfo(id);
		return "redirect:/adminDashboard/DeliveryAddressInfo";
	}
	
	@GetMapping("/orderList")
	public String showOrders(Model model) {
		List<Orders> orders = ordersService.getAllOrders();
		model.addAttribute("orders", orders);
		return "admin/orderList";
	}
	
	
	@GetMapping("/orders/orderHistory")
	public String viewOrderHistory(Model model) {
		List<OrderHistory> orderHistory = orderHistoryService.getAllOrderHistories();
		model.addAttribute("orderHistory", orderHistory);
		return "admin/orderHistory";
	}
	
	@GetMapping("/deleteOrderHistory")
	public String deleteOrderHistory(@RequestParam("id") int id) {
		orderHistoryService.deleteOrderHistory(id);
		return "redirect:/adminDashboard/orders/orderHistory";
	}
}