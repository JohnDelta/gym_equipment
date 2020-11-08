package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "products")
@Table
public class Products {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productsId")
	private Long productsId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description", length = 10000)
	private String description;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "photo1")
	private String photo1;
	
	@Column(name = "photo2")
	private String photo2;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@ManyToOne
	@JoinColumn(name = "categoriesId", referencedColumnName = "categoriesId")
	private Categories categories;

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Long getProductsId() {
		return productsId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getPhoto1() {
		return photo1;
	}

	public String getPhoto2() {
		return photo2;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setProductsId(Long productsId) {
		this.productsId = productsId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productsId == null) ? 0 : productsId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (productsId == null) {
			if (other.productsId != null)
				return false;
		} else if (!productsId.equals(other.productsId))
			return false;
		return true;
	}

}
