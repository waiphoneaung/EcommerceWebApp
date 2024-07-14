package com.g2.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.g2.ecommerce.model.Address;
import com.g2.ecommerce.model.DeliInfo;
import com.g2.ecommerce.model.OrderHistory;
import com.g2.ecommerce.model.Orders;
import com.g2.ecommerce.model.Product;
import com.g2.ecommerce.model.User;
import com.g2.ecommerce.repository.OrderHistoryRepository;
import com.g2.ecommerce.repository.OrdersRepository;
import com.g2.ecommerce.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements CartService {
	
	 @Autowired
	    private OrdersRepository ordersRepository;
	 
	 @Autowired
	 private OrderHistoryRepository orderHistoryRepository;
	 
	 @Autowired
	 @Lazy
	 private AddressService addressService;
	 
	 @Autowired
	 @Lazy
	 private UserService userService;
	 
	 @Autowired
	 private ProductRepository productRepository;
	 
	public Map<Product, Integer> getCart(HttpSession session) {
        @SuppressWarnings("unchecked")
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

	public void addToCart(Product product, HttpSession session) {
        Map<Product, Integer> cart = getCart(session);

        cart.merge(product, 1, Integer::sum);

        session.setAttribute("cart", cart);
        session.setAttribute("cart_size", cart.size());
    }
	
	@Override
    public Orders saveOrder(int user_id,int address_id, List<Integer> quantities, HttpSession session, Orders order) {
		Address address = addressService.getAddressById(address_id);
		double deli_fees = 0;
		for (DeliInfo deli : address.getDeliInfos()) {
			if (deli.getAddress().getId() == address_id) {
				order.setDeli_info(deli);
				deli_fees = address.getDeli_fees();
			}
		}
		
		
		 User user = userService.getUserById(user_id);
	        order.setUser(user);
	        order.setOrder_date(new Date());
	        
        Map<Product, Integer> cart = getCart(session);
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        double totalAmount = 0;
        List<OrderHistory> orderHistories = new ArrayList<>();
        int index = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = quantities.get(index++);
            totalAmount += product.getPrice() * quantity;

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setProduct(product);
            orderHistory.setOrder(order);
            orderHistory.setQuantity(quantity);
            orderHistories.add(orderHistory);
            product.setProduct_qty(product.getProduct_qty() - quantity);
            productRepository.save(product);
        }

        
       
        order.setAmount(totalAmount + deli_fees);
        order.setProducts(new HashSet<>(orderHistories));
        Orders savedOrder = ordersRepository.save(order);
        orderHistoryRepository.saveAll(orderHistories);
        return savedOrder;
    }
	
	@Override
    public void removeFromCart(int productId, HttpSession session) {
        Map<Product, Integer> cart = getCart(session);
        Product productToRemove = null;
        for (Product product : cart.keySet()) {
            if (product.getId() == productId) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {           
            cart.remove(productToRemove);
            session.setAttribute("cart", cart);
            session.setAttribute("cart_size", cart.size());
        }
    }
	
	@Override
	public void clearCart(HttpSession session) {
		session.setAttribute("cart", new HashMap<Product,Integer>());
	}
}
