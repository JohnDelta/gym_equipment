package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "ordersItems")
@Table
public class OrdersItems {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordersItemsId")
	private long ordersItemsId;
	
	@ManyToOne
	@JoinColumn(name = "ordersId", referencedColumnName = "ordersId")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "productsId", referencedColumnName = "productsId")
	private Products products;
	
	@Column(name = "quantity")
	private Long quantity;

	public long getOrdersItemsId() {
		return ordersItemsId;
	}

	public void setOrdersItemsId(long ordersItemsId) {
		this.ordersItemsId = ordersItemsId;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
