package com.john_deligiannis.gym_equipment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "categories")
@Table
public class Categories {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoriesId")
	private long categoriesId;
	
	@Column(name = "title")
	private String title;

	public long getCategoriesId() {
		return categoriesId;
	}

	public String getTitle() {
		return title;
	}

	public void setCategoriesId(long categoriesId) {
		this.categoriesId = categoriesId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
