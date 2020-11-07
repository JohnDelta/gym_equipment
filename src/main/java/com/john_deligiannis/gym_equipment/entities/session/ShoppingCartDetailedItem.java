package com.john_deligiannis.gym_equipment.entities.session;

public class ShoppingCartDetailedItem {
	
	private long productsId;
	private String title;
	private long quantity;
	private long maxQuantity;
	private double price;
	private double totalPrice;
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getProductsId() {
		return productsId;
	}
	
	public void setProductsId(long productsId) {
		this.productsId = productsId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	public long getMaxQuantity() {
		return maxQuantity;
	}
	
	public void setMaxQuantity(long maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
