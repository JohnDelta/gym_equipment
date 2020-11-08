package com.john_deligiannis.gym_equipment.entities.session;

import java.util.HashMap;

import com.john_deligiannis.gym_equipment.entities.Products;

public class DetailedOrder {
	
	private String status;
	private HashMap<Products, Long> products;
	private Double totalPrice;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public HashMap<Products, Long> getProducts() {
		return products;
	}
	
	public void setProducts(HashMap<Products, Long> products) {
		this.products = products;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
