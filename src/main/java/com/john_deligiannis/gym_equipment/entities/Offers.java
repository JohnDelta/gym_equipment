package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "offers")
@Table
public class Offers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offersId")
	private long offersId;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "productsId", referencedColumnName = "productsId")
	private Products products;

	public long getOffersId() {
		return offersId;
	}

	public double getPrice() {
		return price;
	}

	public Products getProducts() {
		return products;
	}

	public void setOffersId(long offersId) {
		this.offersId = offersId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
	
}
