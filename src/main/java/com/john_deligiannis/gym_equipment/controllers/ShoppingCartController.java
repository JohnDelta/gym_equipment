package com.john_deligiannis.gym_equipment.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.queries.Queries;

@Controller
public class ShoppingCartController {

	@RequestMapping(value="/shopping-cart", method=RequestMethod.GET)
    public ModelAndView getShoppingCart(HttpSession session) {
		
		ModelAndView mv = new ModelAndView();
		
		if(Integer.parseInt(session.getAttribute("role").toString()) == 1) {
			Users user = Queries.loadUserByUsername(session.getAttribute("username").toString());
			if(user != null) {
				mv.addObject("USER", user);
			}
		}
		//cart must have, products with id,title,quantity,maxQuantity,price(with offer if have),totalPrice of the above
		mv.addObject("CART", "");
		mv.addObject("LOAD_PANEL", "SHOPPING_CART");
        mv.setViewName("index");
        
        return mv; 
    }
	
}
