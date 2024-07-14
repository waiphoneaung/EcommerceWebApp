package com.g2.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "deli_info")
public class DeliInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "deli_id")
	private Delivery delivery;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "deli_info")
	private Set<Orders> deli_orders = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Orders> getDeli_orders() {
		return deli_orders;
	}

	public void setDeli_orders(Set<Orders> deli_orders) {
		this.deli_orders = deli_orders;
	}

	public DeliInfo() {
	}
}
