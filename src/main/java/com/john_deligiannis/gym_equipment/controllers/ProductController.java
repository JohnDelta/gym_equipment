package com.john_deligiannis.gym_equipment.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.entities.dto.ProductsAndTheirOffer;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class ProductController {

	@RequestMapping(
			value = "/products",
			method = RequestMethod.GET
	)
	public ModelAndView getProducts(
			@RequestParam(value = "productsId", required = false) Long productsId,
			HttpSession session
	) {
		
		if(session.getAttribute("username") == null) {
			new InitializeSession().init(session);
		}
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("LOAD_PANEL", "PRODUCTS");
		mv.addObject("PRODUCTS", Queries.loadProductsAndTheirOffer());
		
		if(productsId != null) {
			mv.addObject("PRODUCT", Queries.loadProductAndItsOffer(productsId));	
		}
		
		mv.setViewName("index");
		
        return mv; 
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(
			value = "/add-product",
			method = RequestMethod.POST
	)
	public ModelAndView addProduct(
			@RequestBody MultiValueMap<String, String> formData,
			HttpSession session,
			HttpServletRequest request,
			ModelMap model
	) {
		
		String path = request.getRequestURI().toString();

		HashMap<Long, Long> cart = (HashMap<Long, Long>) session.getAttribute("cart");
		Long id = Long.parseLong(formData.get("productsId").get(0));
		Long quantity = Long.parseLong(formData.get("productsQuantity").get(0));
		
		if(id > 0 && quantity > 0) {
			if(cart.containsKey(id)) {
				cart.put(id, cart.get(id) + quantity);	
			} else {
				cart.put(id, quantity);
			}
			session.setAttribute("cart", cart);
			
			ProductsAndTheirOffer product = Queries.loadProductAndItsOffer(id);
			Double total = Double.parseDouble(session.getAttribute("total").toString());
			if(product.getOfferPrice() != null) {
				total += quantity * product.getOfferPrice();	
			} else {
				total += quantity * product.getPrice();
			}
			session.setAttribute("total", total);	
		}
		
		boolean inProducts = false;
		for(String sector: path.split("/")) {
			if(sector.equals("products")) {
				inProducts = true;
			}
		}
		
		if(inProducts) {
			return new ModelAndView("redirect:/products", model);
		} else {
			return new ModelAndView("redirect:/", model);
		}
	}
	
}






