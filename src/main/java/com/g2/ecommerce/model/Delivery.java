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
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String phone;
    
    @OneToMany(mappedBy = "delivery")
    private Set<DeliInfo> deliInfos = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<DeliInfo> getDeliInfos() {
        return deliInfos;
    }

    public void setDeliInfos(Set<DeliInfo> deliInfos) {
        this.deliInfos = deliInfos;
    }

    public Delivery() {
    }
}
