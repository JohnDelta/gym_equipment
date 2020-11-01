package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "orders")
@Table
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordersId")
	private long orderId;
	
	@Column(name = "ordersNumber")
	private long orderNumber;
	
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "checked")
	private String checked;
	
	@ManyToOne
	@JoinColumn(name = "usersId", referencedColumnName = "usersId")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "productsId", referencedColumnName = "productsId")
	private Products products;

	public long getOrderId() {
		return orderId;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public long getQuantity() {
		return quantity;
	}

	public String getChecked() {
		return checked;
	}

	public Users getUsers() {
		return users;
	}

	public Products getProducts() {
		return products;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

}
