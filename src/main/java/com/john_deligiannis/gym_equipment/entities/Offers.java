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
	private Long offersId;
	
	@Column(name = "price")
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "productsId", referencedColumnName = "productsId")
	private Products products;

	public Long getOffersId() {
		return offersId;
	}

	public Double getPrice() {
		return price;
	}

	public Products getProducts() {
		return products;
	}

	public void setOffersId(Long offersId) {
		this.offersId = offersId;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
	
}
