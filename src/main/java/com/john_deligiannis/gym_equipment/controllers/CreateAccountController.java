package com.john_deligiannis.gym_equipment.controllers;

import javax.persistence.EntityManager;

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
public class CreateAccountController {
	
	@RequestMapping(
			value = "/create-account",
			method = RequestMethod.GET)
	public ModelAndView getCreate(@RequestParam(value = "fromPage", required = false) String fromPage) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("createAccount", "true");
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
			value = "/create-account",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView postCreate(@RequestBody MultiValueMap<String, String> formData) {
		
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
		user.setRole(0);
		
		if(create(user)) {
			mv.addObject("error", "User created");
		} else {
			mv.addObject("error", "Unable to create user");	
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






