package com.john_deligiannis.gym_equipment.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Offers;

public class Queries {

	public static List<Offers> loadOffers() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
		String query = "SELECT c FROM offers c";
	    TypedQuery<Offers> allQuery = session.createQuery(query, Offers.class);
	    
		return allQuery.getResultList();
	}
	
}
