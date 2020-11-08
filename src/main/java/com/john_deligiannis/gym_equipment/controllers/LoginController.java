package com.john_deligiannis.gym_equipment.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.entities.dto.ProductsAndTheirOffer;
import com.john_deligiannis.gym_equipment.entities.session.ShoppingCartDetailedItem;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class LoginController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = {"/login", "/products/login", "/shopping-cart/login"},
			method = RequestMethod.GET
	)
	public ModelAndView getLogin(
			HttpSession session,
			HttpServletRequest request
	) {
		
		String path = request.getRequestURI().toString();
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) != 1) {
			mv.addObject("LOAD_LOGIN", "TRUE");
			mv.addObject("ERROR", "");	
		}
		
		List<ShoppingCartDetailedItem> detailedItems = new ArrayList<>();
		if(!((HashMap<Long, Long>) session.getAttribute("cart")).isEmpty()) {
			detailedItems = ShoppingCartController.fillShoppingCartDetailed((HashMap<Long, Long>) session.getAttribute("cart"));
		}
		mv.addObject("CART", detailedItems);
		
		mv.addObject("LOAD_PANEL", "SHOPPING_CART");
		
		boolean inProducts = false;
		boolean inShoppingCart = false;
		for(String sector: path.split("/")) {
			if(sector.equals("products")) {
				inProducts = true;
			}
			if(sector.equals("shopping-cart")) {
				inShoppingCart = true;
			}
		}
		
		if(inProducts) {
			mv.addObject("PRODUCTS", Queries.loadProductsAndTheirOffer());
			mv.addObject("FROM_VIEW", "products");
			mv.addObject("LOAD_PANEL", "PRODUCTS");
		} else if(inShoppingCart) {
			
		} else {
			mv.addObject("OFFERS", Queries.loadOffers());
			mv.addObject("FROM_VIEW", "");
			mv.addObject("LOAD_PANEL", "MAIN");	
		}
		
		mv.setViewName("index");
		
        return mv; 
	}

	@RequestMapping(
			value = {"/login", "/products/login", "/shopping-cart/login"},
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public ModelAndView postLogin(
			@RequestBody MultiValueMap<String, String> formData,
			HttpSession session,
			HttpServletRequest request,
			ModelMap model
	) {
		
		String path = request.getRequestURI().toString();
		
		ModelAndView mv = new ModelAndView();
		
		Users user = login(formData.get("username").get(0), formData.get("password").get(0));
		
		if(user != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
		}
		
		boolean inProducts = false;
		boolean inShoppingCart = false;
		for(String sector: path.split("/")) {
			if(sector.equals("products")) {
				inProducts = true;
			}
			if(sector.equals("shopping-cart")) {
				inShoppingCart = true;
			}
		}
		
		if(inProducts) {
			return new ModelAndView("redirect:/products", model);
		} else if(inShoppingCart) {
			return new ModelAndView("redirect:/shopping-cart", model);
		} else {
			return new ModelAndView("redirect:/", model);
		}
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








