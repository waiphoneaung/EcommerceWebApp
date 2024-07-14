package com.g2.ecommerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.model.PaymentType;
import com.g2.ecommerce.model.Product;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.security.LoginUserDetail;
import com.g2.ecommerce.service.CartService;
import com.g2.ecommerce.service.PaymentTypeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrdersController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private PaymentTypeService paymentTypeService;
    

    @PostMapping("/checkout")
    public String checkout(@RequestParam("address_id") int address_id, @RequestParam("quantity") List<Integer> quantities, Model model,Authentication authentication,HttpSession session) {
        Map<Product, Integer> cart = cartService.getCart(session);
        LoginUserDetail loginUserDetail = (LoginUserDetail) authentication.getPrincipal();
        User user = loginUserDetail.getUser();
        int user_id = user.getId();
        if (cart.isEmpty()) {
            model.addAttribute("error", "Cart is empty");
            return "customer/cart";
        }

        Orders order = new Orders();
        List<PaymentType> paymentTypes = paymentTypeService.getAllPaymentTypes();
        cartService.saveOrder(user_id,address_id,quantities,session,order);

       //cartService.clearCart(session);
        model.addAttribute("paymentTypes", paymentTypes);
        model.addAttribute("order", order);
        return "customer/order_confirmation";
    }
}
