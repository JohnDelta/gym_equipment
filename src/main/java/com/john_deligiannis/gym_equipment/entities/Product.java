package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
	private long productId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "photo1")
	private String photo1;
	
	@Column(name = "photo2")
	private String photo2;
	
	@Column(name = "quantity")
	private long quantity;

	public long getProductId() {
		return productId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public String getPhoto1() {
		return photo1;
	}

	public String getPhoto2() {
		return photo2;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
}
