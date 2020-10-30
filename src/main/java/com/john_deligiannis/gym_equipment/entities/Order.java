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
@Table(name = "order")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
	private long orderId;
	
	@Column(name = "orderNumber")
	private long orderNumber;
	
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "checked")
	private String checked;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

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

	public User getUser() {
		return user;
	}

	public Product getProduct() {
		return product;
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

	public void setUser(User user) {
		this.user = user;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
