package com.john_deligiannis.gym_equipment.controllers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class LoginController {
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.GET)
	public ModelAndView getLogin(@RequestParam(value = "fromPage", required = false) String fromPage) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("login", "true");
		mv.addObject("error", "");
		
		if(fromPage.isEmpty()) {
			mv.addObject("fromPage", "");
			mv.addObject("offers", Queries.loadOffers());
			mv.setViewName("index");
		} else { // here load login for other views later
			mv.addObject("fromPage", fromPage);
			mv.setViewName(fromPage);
		}
		
        return mv; 
	}

	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView postLogin(@RequestBody MultiValueMap<String, String> formData) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("login", "true");
		
		Users user = login(formData.get("username").get(0), formData.get("password").get(0));
		if(user != null) {
			mv.addObject("error", "Welcome");
		} else {
			mv.addObject("error", "Unable to login");
		}
		
		if(formData.get("fromPage").isEmpty() || formData.get("formPage") == null) {
			mv.addObject("fromPage", "");
			mv.addObject("offers", Queries.loadOffers());
			mv.setViewName("index");
		} else { // here load login for other views later
			mv.addObject("fromPage", formData.get("fromPage"));
			mv.setViewName("");
		}
		
        return mv; 	
	}
	
	private Users login(String username, String password) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
		Users user = null;
	    
		try {
			TypedQuery<Users> query = session.createQuery(
		    		"SELECT c FROM users c WHERE c.username = :username AND c.password = :password",
		    		Users.class
		    );
		    query.setParameter("username", username);
		    query.setParameter("password", password);
		    
		    user = query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
	    
	    return user;
	}
	
}








