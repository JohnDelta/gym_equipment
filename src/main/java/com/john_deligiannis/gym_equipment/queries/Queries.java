package com.john_deligiannis.gym_equipment.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Offers;
import com.john_deligiannis.gym_equipment.entities.Products;

public class Queries {

	public static List<Offers> loadOffers() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Offers> query = session.createQuery("SELECT c FROM offers c", Offers.class);
	   
		return query.getResultList();
	}
	
	public static List<Products> loadProducts() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Products> query = session.createQuery("SELECT c FROM products c", Products.class);
	    
		return query.getResultList();
	}
	
}
