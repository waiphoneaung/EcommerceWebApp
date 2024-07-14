package com.g2.ecommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date order_date;
	private double amount;

	@OneToOne(mappedBy = "orders")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne()
	@JoinColumn(name = "deli_info_id")
	private DeliInfo deli_info;

	@OneToMany(mappedBy = "order")
	private Set<OrderHistory> products = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DeliInfo getDeli_info() {
		return deli_info;
	}

	public void setDeli_info(DeliInfo deli_info) {
		this.deli_info = deli_info;
	}

	public Set<OrderHistory> getProducts() {
		return products;
	}

	public void setProducts(Set<OrderHistory> products) {
		this.products = products;
	}

	public Orders() {

	}
}
