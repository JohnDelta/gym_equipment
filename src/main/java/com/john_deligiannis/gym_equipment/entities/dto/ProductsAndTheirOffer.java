package com.john_deligiannis.gym_equipment.entities.dto;

public class ProductsAndTheirOffer {

	private Long productsId;
	private String title;
	private String description;
	private Double price;
	private Double offerPrice = -1D;
	private String photo1;
	private String photo2;
	private Long quantity;
	
	public ProductsAndTheirOffer(Long productsId, String title, String description, Double price, Double offerPrice,
			String photo1, String photo2, Long quantity) {
		super();
		this.productsId = productsId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.offerPrice = offerPrice;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.quantity = quantity;
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

	public Double getOfferPrice() {
		return offerPrice;
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

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
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
		
}