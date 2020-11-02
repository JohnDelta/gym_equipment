package com.john_deligiannis.gym_equipment.controllers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class LoginController {
	
	@RequestMapping(
			value = {"/login", "/{fromView}/login"},
			method = RequestMethod.GET
	)
	public ModelAndView getLogin(
			@PathVariable(value = "fromView", required = false) String fromView,
			HttpSession session
	) {
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) != 1) {
			mv.addObject("LOAD_LOGIN", "TRUE");
			mv.addObject("ERROR", "");	
		}
		
		if(fromView == null || fromView.isEmpty()) {
			mv.addObject("OFFERS", Queries.loadOffers());
			mv.addObject("FROM_VIEW", fromView);
			mv.setViewName("index");
		} else {
			
		}
		
        return mv; 
	}

	@RequestMapping(
			value = {"/login", "/{fromView}/login"},
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public ModelAndView postLogin(
			@PathVariable(value = "fromView", required = false) String fromView,
			@RequestBody MultiValueMap<String, String> formData,
			HttpSession session
	) {
		
		ModelAndView mv = new ModelAndView();
		
		Users user = login(formData.get("username").get(0), formData.get("password").get(0));
		
		if(user != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
		} else {
			mv.addObject("LOAD_LOGIN", "TRUE");
			mv.addObject("ERROR", "Unable to login");
		}
		
		if(fromView == null || fromView.isEmpty()) {
			mv.addObject("OFFERS", Queries.loadOffers());
			mv.addObject("FROM_VIEW", fromView);
			mv.setViewName("index");
		} else {
			
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







