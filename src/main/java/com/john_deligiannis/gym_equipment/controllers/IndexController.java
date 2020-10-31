package com.john_deligiannis.gym_equipment.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Offers;

@Controller
public class IndexController {

	@RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView showDefault() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("offers", loadOffers());
        mv.setViewName("index");
        
        return mv; 
    }
	
	public List<Offers> loadOffers() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
		String query = "SELECT c FROM Offers c";
	    TypedQuery<Offers> allQuery = session.createQuery(query, Offers.class);
	    
		return allQuery.getResultList();
	}
	
}
