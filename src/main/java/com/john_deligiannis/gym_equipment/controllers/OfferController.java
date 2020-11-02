package com.john_deligiannis.gym_equipment.controllers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Offers;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class OfferController {
	
	@RequestMapping(
			value = "/offer",
			method = RequestMethod.GET
	)
	public ModelAndView getOffer(@RequestParam("offersId") long offersId) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("OFFERS", Queries.loadOffers());
		mv.addObject("OFFER", loadOffer(offersId));
		mv.addObject("LOAD_PANEL", "MAIN");
		mv.setViewName("index");
		
        return mv; 
	}
	
	private Offers loadOffer(long offersId) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
		Offers offer = null;
		
		try {
			TypedQuery<Offers> query = session.createQuery(
		    		"SELECT c FROM offers c WHERE c.offersId=:offersId",
		    		Offers.class
		    );
		    query.setParameter("offersId", offersId);
		    
		    offer = query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
	    	    
		return offer;
	}

}
