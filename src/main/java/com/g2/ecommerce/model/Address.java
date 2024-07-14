package com.g2.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;
	private double deli_fees;
	private String waiting_time;

	@OneToMany(mappedBy = "address")
	private Set<DeliInfo> deliInfos = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDeli_fees() {
		return deli_fees;
	}

	public void setDeli_fees(double deli_fees) {
		this.deli_fees = deli_fees;
	}

	public String getWaiting_time() {
		return waiting_time;
	}

	public void setWaiting_time(String waiting_time) {
		this.waiting_time = waiting_time;
	}

	public Set<DeliInfo> getDeliInfos() {
		return deliInfos;
	}

	public void setDeliInfos(Set<DeliInfo> deliInfos) {
		this.deliInfos = deliInfos;
	}

	public Address() {
	}
}
