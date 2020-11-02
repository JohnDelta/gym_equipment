package com.john_deligiannis.gym_equipment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class ProductController {

	@RequestMapping(
			value = "/products",
			method = RequestMethod.GET
	)
	public ModelAndView getOffer() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("LOAD_PANEL", "PRODUCTS");
		mv.addObject("PRODUCTS", Queries.loadProducts());
		mv.setViewName("index");
		
        return mv; 
	}
	
}






