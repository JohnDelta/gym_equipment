package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId")
	private long orderId;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
}
