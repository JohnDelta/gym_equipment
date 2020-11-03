package com.john_deligiannis.gym_equipment.controllers;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class CreateAccountController {
	
	@RequestMapping(
			value = "/create-account",
			method = RequestMethod.GET
	)
	public ModelAndView getCreate(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) == 0) {
			mv.addObject("LOAD_CREATE_ACCOUNT", "TRUE");
			mv.addObject("ERROR", "");	
		}
		
		mv.addObject("OFFERS", Queries.loadOffers());
		mv.addObject("FROM_VIEW", "");
		mv.addObject("LOAD_PANEL", "MAIN");
		mv.setViewName("index");
		
        return mv; 
	}
	
	@RequestMapping(
			value = "/create-account",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public ModelAndView postLogin(
			@RequestBody MultiValueMap<String, String> formData,
			HttpSession session
	) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("createAccount", "true");
		
		Users user = new Users();
		user.setUsername(formData.get("username").get(0));
		user.setPassword(formData.get("password").get(0));
		user.setName(formData.get("name").get(0));
		user.setLastname(formData.get("lastname").get(0));
		user.setEmail(formData.get("email").get(0));
		user.setAddress(formData.get("address").get(0));
		user.setCity(formData.get("city").get(0));
		user.setPhone(formData.get("phone").get(0));
		user.setRole(1);
		
        if(create(user)) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
		} else {
			mv.addObject("LOAD_CREATE_ACCOUNT", "TRUE");
			mv.addObject("ERROR", "Unable to create account");
		}
		
        mv.addObject("OFFERS", Queries.loadOffers());
		mv.addObject("FROM_VIEW", "");
		mv.setViewName("index");
		
        return mv;
	}
	
	private boolean create(Users user) {
		boolean flag = true;
		
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
    	em.getTransaction().begin();
    	em.persist(user);
    	em.getTransaction().commit();
    	em.close();
		
		return flag;
	}

}






