package com.g2.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.g2.ecommerce.dto.ProfileDto;
//import com.g2.ecommerce.dto.UserDto;
//import com.g2.ecommerce.model.Role;
//import com.g2.ecommerce.model.User;
//import com.g2.ecommerce.service.RoleService;
//import com.g2.ecommerce.service.UserService;
//
//import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.model.Delivery;
import com.g2.ecommerce.model.OrderHistory;
import com.g2.ecommerce.model.Product;
import com.g2.ecommerce.model.Profile;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.security.LoginUserDetail;
import com.g2.ecommerce.service.AddressService;
import com.g2.ecommerce.service.CartService;
import com.g2.ecommerce.service.DeliveryService;
import com.g2.ecommerce.service.OrderHistoryService;
import com.g2.ecommerce.service.PaymentService;
import com.g2.ecommerce.service.ProductService;
import com.g2.ecommerce.service.ProfileService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ProductService productService;
	
	@Autowired
    private CartService cartService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@GetMapping({"", "/"})
    public String mainPage(Model model,HttpSession session,Authentication authentication) {
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
        if (session.getAttribute("cart") == null) {
            Map<Product, Integer> cart = new HashMap<>();
            session.setAttribute("cart", cart);
            session.setAttribute("cart_size", cart.size());
        }
        model.addAttribute("cart_size", session.getAttribute("cart_size"));
        model.addAttribute("profileId",profile_id);
        return "customer/index";
    }
	
	@GetMapping("/products")
	public String showProducts(Model model,HttpSession session, Authentication authentication) {
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
		List<Product> products = productService.getAllProducts();
//		long productCount = productService.getProductCount();
		model.addAttribute("products", products);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
//		model.addAttribute("productCount", productCount);
		return "customer/product";
	}
	
	@GetMapping("/vege")
	public String showVegetable(Model model,HttpSession session, Authentication authentication) {
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
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/vege";
	}
	
	@GetMapping("/meat")
	public String showMeat(Model model,HttpSession session, Authentication authentication) {
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
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/meat";
	}
	
	@GetMapping("/fruit")
	public String showFruit(Model model,HttpSession session, Authentication authentication) {
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
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/fruit";
	}
	
	@GetMapping("/juice")
	public String showJuice(Model model,HttpSession session, Authentication authentication) {
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
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/juice";
	}
	
	@GetMapping("/dried")
	public String showDried(Model model,HttpSession session, Authentication authentication) {
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
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/dried";
	}
	
	@GetMapping("/about")
	public String showAbout(Model model, Authentication authentication, HttpSession session) {
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
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/about";
	}
	
	@GetMapping("/contact")
	public String showContact(Model model, Authentication authentication, HttpSession session) {
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
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/contact";
	}

	@GetMapping("/details")
	public String showProductDetails(@RequestParam("id") int id, Model model, HttpSession session,Authentication authentication) {
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
	    Optional<Product> detailsProduct = productService.detailsProduct(id);
	    if (detailsProduct.isPresent()) {
	        model.addAttribute("detailsProduct", detailsProduct.get());
	    } else {
	        model.addAttribute("errorMessage", "Product not found");
	    }
	    model.addAttribute("profileId",profile_id);
	    model.addAttribute("cart_size", session.getAttribute("cart_size"));
	    return "customer/details_product";
	}

	@GetMapping("/cart")
    public String cartPage(Model model, HttpSession session, Authentication authentication) {
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
        Map<Product, Integer> cart = cartService.getCart(session);
		List<Address> addresses = addressService.getAllAddresses();
		List<Delivery> deliveries = deliveryService.getAllDeliveries();
        model.addAttribute("cart", cart);
		model.addAttribute("cartService", cartService);
		model.addAttribute("addresses", addresses);
		model.addAttribute("deliveries", deliveries);
        double total = cart.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        model.addAttribute("total", total);
        model.addAttribute("profileId",profile_id);
        model.addAttribute("cart_size", session.getAttribute("cart_size"));
        return "customer/cart";
    }
	
	@GetMapping("/addToCart")
	public String addToCart(@RequestParam("id") int id,HttpSession session, Model model, Authentication authentication) {
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
		Product product = productService.getProductById(id);
		cartService.addToCart(product, session);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "redirect:/customer/cart";
	}
	
	@PostMapping("/payment")
	public String orderPayment(@RequestParam("paymentType_id") int paymentType_id,@RequestParam int order_id,HttpSession session, Model model, Authentication authentication) {
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
		paymentService.savePayment(paymentType_id, order_id);
		cartService.clearCart(session);
		model.addAttribute("profileId",profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/successful";
	}
	
	@GetMapping("/orderHistory")
	public String orderHistory(Model model,Authentication authentication,HttpSession session) {
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
		List<OrderHistory> orderHistories = orderHistoryService.getAllOrderHistories();
		model.addAttribute("orderHistories", orderHistories);
		model.addAttribute("userId", user_id);
		model.addAttribute("profileId", profile_id);
		model.addAttribute("cart_size", session.getAttribute("cart_size"));
		return "customer/orderHistory";
	}
	
	@GetMapping("/removed")
	public String removeFromCart(@RequestParam("id") int id, HttpSession session, Model model, Authentication authentication) {
	    LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
	    User user = userDetail.getUser();
	    int user_id = user.getId();
	    List<Profile> profiles = profileService.getAllProfiles();

	    int profile_id = 0;
	    for (Profile profile : profiles) {
	        if (profile.getUser().getId() == user_id) {
	            profile_id = profile.getId();
	        }
	    }

	    cartService.removeFromCart(id, session);
	    model.addAttribute("profileId", profile_id);
	    return "redirect:/customer/cart";
	}
}
